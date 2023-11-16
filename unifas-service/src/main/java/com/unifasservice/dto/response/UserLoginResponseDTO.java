package com.unifasservice.dto.response;

import lombok.Data;

@Data
public class UserLoginResponseDTO {
    private long id;
    private String username;
    private String message;
    private String token;
    private String role;

}
