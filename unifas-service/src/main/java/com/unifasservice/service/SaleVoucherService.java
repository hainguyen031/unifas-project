package com.unifasservice.service;


import com.unifasservice.dto.payload.CommonResponse;

public interface SaleVoucherService {
    CommonResponse checkVoucherValid(String code);
}
