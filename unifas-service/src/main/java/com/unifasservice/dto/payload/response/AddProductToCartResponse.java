package com.unifasservice.dto.payload.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddProductToCartResponse {
    private long id;
    private int quantity;
    private double price;
    private double subtotal;
}
