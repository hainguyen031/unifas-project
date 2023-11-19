package com.unifasservice.dto.payload.request;


import lombok.Data;

@Data
public class AddProductToCartRequest {
    private long productId;
    private int quantity;
    private long variantId;

}
