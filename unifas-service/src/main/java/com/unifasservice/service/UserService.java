package com.unifasservice.service;


import com.unifasservice.dto.request.ChangePassReqDTO;
import com.unifasservice.dto.request.ForgetPassReqDTO;
import com.unifasservice.dto.request.UserLoginRequestDTO;
import com.unifasservice.dto.request.UserRegisterRequestDTO;
import com.unifasservice.dto.response.CodePassResponseDTO;
import com.unifasservice.dto.response.PassResponseDTO;
import com.unifasservice.dto.response.UserLoginResponseDTO;
import com.unifasservice.dto.response.UserRegisterResponseDTO;


public interface UserService {
    UserLoginResponseDTO login(UserLoginRequestDTO login);
    UserRegisterResponseDTO register(UserRegisterRequestDTO userResgisterRequestDTO);
    CodePassResponseDTO createCodePass(ForgetPassReqDTO forgetPassReqDTO);
    boolean changePass(ChangePassReqDTO changePassReqDTO);
}
