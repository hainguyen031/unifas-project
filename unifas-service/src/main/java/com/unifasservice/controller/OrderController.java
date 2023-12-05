package com.unifasservice.controller;

import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.dto.payload.request.OrderRequest;
import com.unifasservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;

    public ResponseEntity<CommonResponse> createOrder(Authentication authentication, @RequestBody OrderRequest orderRequest){
        try {
            CommonResponse commonResponse = orderService.createOrder(authentication, orderRequest);
            return new ResponseEntity<>(commonResponse, HttpStatus.OK);
        }catch (IllegalArgumentException e){
            CommonResponse commonResponse = CommonResponse.builder()
                    .data(false)
                    .message(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(commonResponse);
        }catch (Exception e){
            CommonResponse commonResponse = CommonResponse.builder()
                    .data(false)
                    .message(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(commonResponse);
        }

    }



}
