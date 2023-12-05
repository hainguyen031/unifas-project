package com.unifasservice.service;

import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.dto.payload.request.UserLoginRequest;
import com.unifasservice.dto.payload.request.UserRegisterRequest;
import com.unifasservice.dto.payload.request.ChangePassRequest;
import com.unifasservice.dto.payload.request.ForgetPassRequest;
import com.unifasservice.dto.payload.response.CodePassResponse;


public interface UserService {
    CommonResponse login(UserLoginRequest login);
    CommonResponse register(UserRegisterRequest userResgisterRequest);
    CodePassResponse createCodePass(ForgetPassRequest forgetPassRequest);
    boolean changePass(ChangePassRequest changePassRequest);
}
