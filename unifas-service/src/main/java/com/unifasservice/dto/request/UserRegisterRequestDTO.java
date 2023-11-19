package com.unifasservice.dto.request;

import lombok.Data;

@Data
public class UserRegisterRequestDTO {
    private String username;
    private String password;
    private String email;
}
