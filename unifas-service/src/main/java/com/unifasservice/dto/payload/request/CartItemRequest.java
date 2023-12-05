package com.unifasservice.dto.payload.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CartItemRequest {
    private long id;
}
