package com.unifasservice.converter;


import com.unifasservice.dto.response.UserLoginResponseDTO;
import com.unifasservice.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserLoginConverter {
    public UserLoginResponseDTO userToUserLoginDTO (User user) {
        UserLoginResponseDTO userLoginResponseDTO = new UserLoginResponseDTO();
        userLoginResponseDTO.setId(user.getId());
        userLoginResponseDTO.setUsername(user.getUsername());
        return userLoginResponseDTO;
    }


}
