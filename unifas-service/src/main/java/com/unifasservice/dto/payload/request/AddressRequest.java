package com.unifasservice.dto.payload.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AddressRequest {
    private long id;
    private String street;
    private String ward;
    private String district;
    private String city;
    private String contact;
    private String receiver;
    private String isDefault;
}
