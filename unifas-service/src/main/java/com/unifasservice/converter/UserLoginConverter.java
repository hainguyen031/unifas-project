package com.unifasservice.converter;


import com.unifasservice.dto.payload.response.UserLoginResponse;
import com.unifasservice.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserLoginConverter {
    public UserLoginResponse userToUserLoginDTO (User user) {
        UserLoginResponse userLoginResponseDTO = new UserLoginResponse();
        userLoginResponseDTO.setId(user.getId());
        userLoginResponseDTO.setUsername(user.getUsername());
        return userLoginResponseDTO;
    }


}
