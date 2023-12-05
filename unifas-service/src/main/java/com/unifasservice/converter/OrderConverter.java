package com.unifasservice.converter;

import com.unifasservice.dto.payload.request.OrderRequest;
import com.unifasservice.dto.payload.response.OrderResponse;
import com.unifasservice.entity.CartItem;
import com.unifasservice.entity.Order;
import com.unifasservice.entity.OrderLine;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class OrderConverter {
    OrderLineConverter orderLineConverter;
    public Order convertOrderRequestToEntity(OrderRequest orderRequest){
        return Order.builder()
                .date(LocalDateTime.now())
                .totalAmount(orderRequest.getTotalAmount())
                .finalPrice(orderRequest.getFinalPrice())
                .payment(orderRequest.getPayment())
                .build();
    }


    public OrderResponse convertOrderEntityToResponse(Order orderAfterSave) {
        return OrderResponse.builder()
                .id(orderAfterSave.getId())
                .date(orderAfterSave.getDate())
                .totalAmount(orderAfterSave.getTotalAmount())
                .finalPrice(orderAfterSave.getFinalPrice())
                .payment(orderAfterSave.getPayment())
                .orderLineResponseList(orderLineConverter.convertEntityListToDtoListResponse(orderAfterSave.getOrderLineList()))
                .build();
    }
}
