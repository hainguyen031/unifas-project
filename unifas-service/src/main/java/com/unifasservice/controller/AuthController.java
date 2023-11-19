package com.unifasservice.controller;

import com.unifasservice.dto.payload.request.UserLoginRequest;
import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.dto.payload.request.UserRegisterRequest;
import com.unifasservice.dto.request.ChangePassRequestDto;
import com.unifasservice.dto.request.ForgetPassRequestDto;
import com.unifasservice.dto.response.CodePassResponseDto;
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
    public ResponseEntity<CodePassResponseDto> forgetPass(@RequestBody ForgetPassRequestDto forgetPassReqDTO) {
        CodePassResponseDto responseDTO = userService.createCodePass(forgetPassReqDTO);
        if (responseDTO != null) {
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePass(@RequestBody ChangePassRequestDto changePassReqDTO) {
        boolean passResponseDTO = userService.changePass(changePassReqDTO);
        if (passResponseDTO) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
