package com.unifasservice.service.impl;

import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.dto.request.ChangePassRequestDto;
import com.unifasservice.dto.request.ForgetPassRequestDto;
import com.unifasservice.dto.response.CodePassResponseDto;
import com.unifasservice.dto.response.DataMailDto;
import com.unifasservice.security.JwtTokenUtil;
import com.unifasservice.converter.UserLoginConverter;
import com.unifasservice.dto.payload.request.UserLoginRequest;
import com.unifasservice.dto.payload.response.UserLoginResponse;
import com.unifasservice.entity.Cart;
import com.unifasservice.entity.User;
import com.unifasservice.repository.UserRepository;
import com.unifasservice.converter.UserRegisterConverter;
import com.unifasservice.dto.payload.request.UserRegisterRequest;
import com.unifasservice.service.MailService;
import com.unifasservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.MessagingException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.unifasservice.utilities.RandomStringGenerator.generateRandomString;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserLoginConverter userLoginConverter;
    private final UserRegisterConverter userRegisterConverter;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final MailService mailService;


    public CommonResponse login(UserLoginRequest login) {

        CommonResponse commonResponse = new CommonResponse();

        String email = login.getEmail();
        String password = login.getPassword();

        if (email == null || password == null) {
            throw new IllegalArgumentException("Username and password are required.");
        }

        User user = userRepository.findByEmail(email);

        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {

                UserLoginResponse loginResponseDTO = userLoginConverter.userToUserLoginDTO(user);


                String token = jwtTokenUtil.generateToken(user);
                loginResponseDTO.setToken(token);
                loginResponseDTO.setRole(user.getRole());


                commonResponse.builder()
                        .message("Logged in Successfully !")
                        .statusCode(HttpStatus.OK)
                        .data(loginResponseDTO)
                        .build();
                return commonResponse;


            } else {
                throw new RuntimeException("Wrong password");
            }
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public CommonResponse register(UserRegisterRequest userRegisterRequestDTO) {

        String password = userRegisterRequestDTO.getPassword();
        String hashCode = passwordEncoder.encode(password);

        User userNameCheck = userRepository.findByUsername(userRegisterRequestDTO.getUsername());
        if (userNameCheck != null) {
            return getCommonResponse("This account already exists", HttpStatus.BAD_REQUEST, false);
        }
        User emailCheck = userRepository.findByEmail(userRegisterRequestDTO.getEmail());
        if (emailCheck != null) {
            return getCommonResponse("Email exists, please enter another email!", HttpStatus.BAD_REQUEST, false);
        }
        try {

            User user = userRegisterConverter.convertDTORequestToEntity(userRegisterRequestDTO);
            user.setPassword(hashCode);
            user.setDeleted(false);

            Cart cartUser = new Cart();
            cartUser.setUser(user);
            user.setCart(cartUser);
            userRepository.save(user);

        } catch (Exception e) {
            return getCommonResponse("Register Failure!", HttpStatus.BAD_REQUEST, false);
        }
        return getCommonResponse("Register Success!", HttpStatus.CREATED, true);
    }

    private CommonResponse getCommonResponse(String message, HttpStatus status, Object data) {
        CommonResponse commonResponse = CommonResponse.builder()
                .message(message)
                .statusCode(status)
                .data(data)
                .build();
        return commonResponse;

    }
    @Override
    public CodePassResponseDto createCodePass(ForgetPassRequestDto forgetPassReqDTO) {
        User emailCheck = userRepository.findByEmail(forgetPassReqDTO.getEmail());
        if (emailCheck != null) {
            String randomString = generateRandomString(6);
            CodePassResponseDto responseDTO = new CodePassResponseDto();
            responseDTO.setCodePass(randomString);
            try {
                DataMailDto dataMail = new DataMailDto();
                dataMail.setTo(forgetPassReqDTO.getEmail());
                dataMail.setSubject("Yêu cầu đặt lại mật khẩu");
                Map<String, Object> props = new HashMap<>();
                props.put("codePass", randomString);
                dataMail.setProps(props);
                mailService.sendHtmlMail(dataMail, "forgot-password");
            } catch (MessagingException exp){
                exp.printStackTrace();
            } catch (javax.mail.MessagingException e) {
                throw new RuntimeException(e);
            }
            return responseDTO;
        }
        return null;
    }

    @Override
    public boolean changePass(ChangePassRequestDto changePassReqDTO) {
        User emailCheck = userRepository.findByEmail(changePassReqDTO.getEmail());
        if (emailCheck != null) {
            String hashCode = passwordEncoder.encode(changePassReqDTO.getNewPass());
            emailCheck.setPassword(hashCode);
            userRepository.save(emailCheck);
            return true;
        }
        return false;
    }

}
