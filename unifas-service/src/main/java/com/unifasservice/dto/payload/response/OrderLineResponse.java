package com.unifasservice.dto.payload.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrderLineResponse {
    private long id;
    private String productName;
    private long price;
    private int quantity;
    private long subtotal;
    private String size;
    private String color;
}
