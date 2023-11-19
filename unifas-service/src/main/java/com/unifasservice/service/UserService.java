package com.unifasservice.service;

import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.dto.payload.request.UserLoginRequest;
import com.unifasservice.dto.payload.request.UserRegisterRequest;
import com.unifasservice.dto.request.ChangePassRequestDto;
import com.unifasservice.dto.request.ForgetPassRequestDto;
import com.unifasservice.dto.response.CodePassResponseDto;


public interface UserService {
    CommonResponse login(UserLoginRequest login);
    CommonResponse register(UserRegisterRequest userResgisterRequestDTO);
    CodePassResponseDto createCodePass(ForgetPassRequestDto forgetPassReqDTO);
    boolean changePass(ChangePassRequestDto changePassReqDTO);
}
