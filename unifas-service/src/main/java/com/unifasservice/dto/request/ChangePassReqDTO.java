package com.unifasservice.dto.request;

import lombok.Data;

@Data
public class ChangePassReqDTO {
    private String email;
    private String newPass;
}
