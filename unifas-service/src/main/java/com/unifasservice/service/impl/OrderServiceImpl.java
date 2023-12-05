package com.unifasservice.service.impl;

import com.unifasservice.converter.CartItemConverter;
import com.unifasservice.converter.OrderConverter;
import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.dto.payload.request.CartItemRequest;
import com.unifasservice.dto.payload.request.OrderRequest;
import com.unifasservice.dto.payload.response.OrderResponse;
import com.unifasservice.entity.*;
import com.unifasservice.repository.*;
import com.unifasservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderRepository orderRepository;

    private final OrderConverter orderConverter;
    private final CartItemConverter cartItemConverter;

    @Override
    public CommonResponse createOrder(Authentication authentication, OrderRequest orderRequest) {
        String username = authentication.getName();
        User userEntity = userRepository.findByUsername(username);
        Address address = addressRepository.findById(orderRequest.getAddressId()).orElseThrow(
                () -> new IllegalArgumentException("Address not found")
        );

        Order order = orderConverter.convertOrderRequestToEntity(orderRequest);
        order.setAddress(address);
        order.setUser(userEntity);
        order.setOrderLineList(Collections.singletonList(new OrderLine()));

        Order orderEntity = orderRepository.save(order);

        List<CartItem> cartItemList = cartItemConverter.convertListDTORequestToListEntity(orderRequest.getCartItemDtoList());
        List<OrderLine> orderLineList = new ArrayList<>();
        for (CartItem cartItem : cartItemList){
            CartItem cartItemEntity = cartItemRepository.findById(cartItem.getId()).orElseThrow(() -> new IllegalArgumentException("CartItem not found"));

            int quantity = cartItemEntity.getQuantity();
            long price = cartItemEntity.getPrice();

            OrderLine orderLine = OrderLine.builder()
                    .quantity(quantity)
                    .price(price)
                    .subtotal(quantity * price)
                    .product(cartItemEntity.getProduct())
                    .variant(cartItemEntity.getVariant())
                    .order(orderEntity)
                    .build();

            orderLineList.add(orderLine);
        }
        orderEntity.setOrderLineList(orderLineList);
        Order orderAfterSave = orderRepository.save(orderEntity);

        OrderResponse orderResponse = orderConverter.convertOrderEntityToResponse(orderAfterSave);


        return CommonResponse.builder()
                .data(orderResponse)
                .message("Order Successfully")
                .statusCode(HttpStatus.OK)
                .build()
                ;

    }
}
