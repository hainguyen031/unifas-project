package com.unifasservice.dto.payload.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SaleVoucherRequest {
    private long id;
    private String code;

}
