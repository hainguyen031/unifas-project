package com.unifasservice.dto.payload.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SaleVoucherResponse {
    private long id;
    private double discount;
}
