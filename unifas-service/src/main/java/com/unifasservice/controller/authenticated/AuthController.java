package com.unifasservice.controller.authenticated;


import com.unifasservice.dto.request.ChangePassReqDTO;
import com.unifasservice.dto.request.ForgetPassReqDTO;
import com.unifasservice.dto.request.UserLoginRequestDTO;
import com.unifasservice.dto.response.CodePassResponseDTO;
import com.unifasservice.dto.response.PassResponseDTO;
import com.unifasservice.dto.response.UserLoginResponseDTO;
import com.unifasservice.dto.request.UserRegisterRequestDTO;
import com.unifasservice.dto.response.UserRegisterResponseDTO;
import com.unifasservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class AuthController {



    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDTO> login(@Valid @RequestBody UserLoginRequestDTO loginRequestDTO,
                                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            UserLoginResponseDTO loginResponseDTO = userService.login(loginRequestDTO);
            return new ResponseEntity<>(loginResponseDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    @PostMapping(value = "/register")
    public ResponseEntity<UserRegisterResponseDTO> registerUser(@RequestBody UserRegisterRequestDTO userRegisterRequestDTO) {
        UserRegisterResponseDTO responseDto = userService.register(userRegisterRequestDTO);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PostMapping("/forget-password")
    public ResponseEntity<CodePassResponseDTO> forgetPass(@RequestBody ForgetPassReqDTO forgetPassReqDTO) {
        CodePassResponseDTO responseDTO = userService.createCodePass(forgetPassReqDTO);
        if (responseDTO != null) {
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePass(@RequestBody ChangePassReqDTO changePassReqDTO) {
        boolean passResponseDTO = userService.changePass(changePassReqDTO);
        if (passResponseDTO) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
