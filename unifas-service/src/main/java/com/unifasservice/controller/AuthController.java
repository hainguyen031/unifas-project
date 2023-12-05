package com.unifasservice.controller;

import com.unifasservice.dto.payload.request.UserLoginRequest;
import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.dto.payload.request.UserRegisterRequest;
import com.unifasservice.dto.payload.request.ChangePassRequest;
import com.unifasservice.dto.payload.request.ForgetPassRequest;
import com.unifasservice.dto.payload.response.CodePassResponse;
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
    public ResponseEntity<CommonResponse> login(@Valid @RequestBody UserLoginRequest loginRequest,
                                                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            CommonResponse loginResponseDTO = userService.login(loginRequest);
            return new ResponseEntity<>(loginResponseDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    @PostMapping(value = "/register")
    public ResponseEntity<CommonResponse> registerUser(@RequestBody UserRegisterRequest userRegisterRequest) {
        CommonResponse responseDto = userService.register(userRegisterRequest);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
    @PostMapping("/forget-password")
    public ResponseEntity<CodePassResponse> forgetPass(@RequestBody ForgetPassRequest forgetPassRequest) {
        CodePassResponse responseDto = userService.createCodePass(forgetPassRequest);
        if (responseDto != null) {
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePass(@RequestBody ChangePassRequest changePassRequest) {
        boolean passResponse= userService.changePass(changePassRequest);
        if (passResponse) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
