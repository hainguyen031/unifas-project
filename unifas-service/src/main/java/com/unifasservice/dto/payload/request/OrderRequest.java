package com.unifasservice.dto.payload.request;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private long id;
    private long addressId;
    private long totalAmount;
    private long finalPrice;
    private String payment;
    private List<CartItemRequest> cartItemDtoList;

}
