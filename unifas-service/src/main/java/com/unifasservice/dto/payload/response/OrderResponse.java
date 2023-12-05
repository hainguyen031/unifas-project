package com.unifasservice.dto.payload.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderResponse {
    private long id;
    private LocalDateTime date;
    private long totalAmount;
    private long finalPrice;
    private String payment;
    private List<OrderLineResponse> orderLineResponseList;

}
