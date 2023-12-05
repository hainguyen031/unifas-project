package com.unifasservice.service.impl;

import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.dto.payload.response.SaleVoucherResponse;
import com.unifasservice.entity.SaleVoucher;
import com.unifasservice.repository.SaleVoucherRepository;
import com.unifasservice.service.SaleVoucherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class saleVoucherServiceImpl implements SaleVoucherService {

    private final SaleVoucherRepository saleVoucherRepository;

    @Override
    public CommonResponse checkVoucherValid(String code) {
        SaleVoucher saleVoucherEntity = saleVoucherRepository.findByCode(code);
        if (saleVoucherEntity == null) {
            return CommonResponse.builder()
                    .data(false)
                    .message("Voucher is invalid or expired")
                    .statusCode(HttpStatus.BAD_REQUEST)
                    .build();
        }

        SaleVoucherResponse saleVoucherResponse = SaleVoucherResponse
                .builder()
                .id(saleVoucherEntity.getId())
                .discount(saleVoucherEntity.getDiscount())
                .build();
        return CommonResponse
                .builder()
                .data(saleVoucherResponse)
                .message("Valid voucher")
                .statusCode(HttpStatus.OK)
                .build();
    }
}
