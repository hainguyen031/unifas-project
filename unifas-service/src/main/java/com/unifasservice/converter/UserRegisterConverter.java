package com.unifasservice.converter;


import com.unifasservice.dto.payload.request.UserRegisterRequest;
import com.unifasservice.dto.payload.response.UserRegisterResponse;
import com.unifasservice.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserRegisterConverter {
    public UserRegisterResponse convertEntityResponseToDTO(User user){
        UserRegisterResponse userRegisterResponseDTO = new UserRegisterResponse();
        userRegisterResponseDTO.setId(user.getId());
        userRegisterResponseDTO.setUsername(user.getUsername());
        return userRegisterResponseDTO;
    }
    public User convertDTORequestToEntity(UserRegisterRequest userRegisterRequestDTO){
        User user = new User();
        user.setUsername(userRegisterRequestDTO.getUsername());
        user.setPassword(userRegisterRequestDTO.getPassword());
        user.setEmail(userRegisterRequestDTO.getEmail());
        user.setRole("ROLE_USER");
        return user;
    }
}
