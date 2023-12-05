package com.unifasservice.converter;

import com.unifasservice.dto.payload.response.OrderLineResponse;
import com.unifasservice.entity.CartItem;
import com.unifasservice.entity.OrderLine;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderLineConverter {
    public OrderLineResponse convertEntityToDtoResponse(OrderLine orderLine){
        return OrderLineResponse.builder()
                .id(orderLine.getId())
                .productName(orderLine.getProduct().getName())
                .price(orderLine.getPrice())
                .quantity(orderLine.getQuantity())
                .subtotal(orderLine.getSubtotal())
                .size(orderLine.getVariant().getSize().getName())
                .color(orderLine.getVariant().getColor().getAcronym())
                .build();
    }

    public List<OrderLineResponse> convertEntityListToDtoListResponse(List<OrderLine> orderLineList){
        return orderLineList.stream().map(this::convertEntityToDtoResponse).collect(Collectors.toList());
    }
}
