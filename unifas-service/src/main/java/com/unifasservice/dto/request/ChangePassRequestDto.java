package com.unifasservice.dto.request;

import lombok.Data;

@Data
public class ChangePassRequestDto {
    private String email;
    private String newPass;
}
