package com.unifasservice.service.impl;

import com.unifasservice.configuration.JwtTokenUtil;
import com.unifasservice.converter.UserLoginConverter;
import com.unifasservice.dto.request.ChangePassReqDTO;
import com.unifasservice.dto.request.ForgetPassReqDTO;
import com.unifasservice.dto.request.UserLoginRequestDTO;
import com.unifasservice.dto.response.*;
import com.unifasservice.entity.User;
import com.unifasservice.repository.UserRepository;
import com.unifasservice.converter.UserRegisterConverter;
import com.unifasservice.dto.request.UserRegisterRequestDTO;
import com.unifasservice.service.MailService;
import com.unifasservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;

import static com.unifasservice.utilities.RandomStringGenerator.generateRandomString;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserLoginConverter userLoginConverter;
    @Autowired
    private UserRegisterConverter userRegisterConverter;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private MailService mailService;

    public UserLoginResponseDTO login(UserLoginRequestDTO login) {
        String email = login.getEmail();
        String password = login.getPassword();

        if (email == null || password == null) {
            throw new IllegalArgumentException("Username and password are required.");
        }

        User user = userRepository.findByEmail(email);

        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                UserLoginResponseDTO loginResponseDTO = userLoginConverter.userToUserLoginDTO(user);
                loginResponseDTO.setMessage("Logged in successfully !");

                String token = jwtTokenUtil.generateToken(user);
                loginResponseDTO.setToken(token);
                loginResponseDTO.setRole(user.getRole());


                return loginResponseDTO;
            } else {
                throw new RuntimeException("Wrong password");
            }
        } else {
            throw new RuntimeException("User not found");
        }
    }
    @Override
    public UserRegisterResponseDTO register(UserRegisterRequestDTO userRegisterRequestDTO) {

        String password = userRegisterRequestDTO.getPassword();
        String hashCode = passwordEncoder.encode(password);

        UserRegisterResponseDTO responseDTO = new UserRegisterResponseDTO();
        try {
            User userNameCheck = userRepository.findByUsername(userRegisterRequestDTO.getUsername());
            if (userNameCheck != null) {
                responseDTO.setMessage("This account already exists");
                return responseDTO;
            }
            User emailCheck = userRepository.findByEmail(userRegisterRequestDTO.getEmail());
            if (emailCheck != null) {
                responseDTO.setMessage("Email exists, please enter another email!");
                return responseDTO;
            }
            User user = userRegisterConverter.convertDTORequestToEntity(userRegisterRequestDTO);
            user.setPassword(hashCode);
            user.setDeleted(false);
            userRepository.save(user);
            responseDTO = userRegisterConverter.convertEntityResponseToDTO(user);
            responseDTO.setMessage("Register Success!");
            return responseDTO;

        } catch (Exception e) {
            responseDTO.setMessage("Register Failure!");
            return responseDTO;
        }

    }

    @Override
    public CodePassResponseDTO createCodePass(ForgetPassReqDTO forgetPassReqDTO) {
        User emailCheck = userRepository.findByEmail(forgetPassReqDTO.getEmail());
        if (emailCheck != null) {
            String randomString = generateRandomString(6);
            CodePassResponseDTO responseDTO = new CodePassResponseDTO();
            responseDTO.setCodePass(randomString);
            try {
            DataMailDTO dataMail = new DataMailDTO();
            dataMail.setTo(forgetPassReqDTO.getEmail());
            dataMail.setSubject("Yêu cầu đặt lại mật khẩu");
            Map<String, Object> props = new HashMap<>();
            props.put("codePass", randomString);
            dataMail.setProps(props);
            mailService.sendHtmlMail(dataMail, "forgot-password");
            } catch (MessagingException exp){
                exp.printStackTrace();
            }
            return responseDTO;
        }
        return null;
    }

    @Override
    public boolean changePass(ChangePassReqDTO changePassReqDTO) {
        User emailCheck = userRepository.findByEmail(changePassReqDTO.getEmail());
        if (emailCheck != null) {
            emailCheck.setPassword(changePassReqDTO.getNewPass());
            userRepository.save(emailCheck);
            return true;
        }
        return false;
    }
}
