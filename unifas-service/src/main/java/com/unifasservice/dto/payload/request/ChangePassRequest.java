package com.unifasservice.dto.payload.request;

import lombok.Data;

@Data
public class ChangePassRequest {
    private String email;
    private String newPass;
}
