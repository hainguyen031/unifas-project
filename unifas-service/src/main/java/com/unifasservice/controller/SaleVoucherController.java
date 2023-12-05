package com.unifasservice.controller;


import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.dto.payload.request.SaleVoucherRequest;
import com.unifasservice.service.SaleVoucherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
@CrossOrigin("*")
public class SaleVoucherController {

    private final SaleVoucherService saleVoucherService;

    @PostMapping("/sale-voucher")
    public ResponseEntity<CommonResponse> checkVoucherValid(@RequestBody SaleVoucherRequest saleVoucherRequest){
        String code = saleVoucherRequest.getCode();
        CommonResponse commonResponse = saleVoucherService.checkVoucherValid(code);
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

}
