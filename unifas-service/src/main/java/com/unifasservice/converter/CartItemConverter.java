package com.unifasservice.converter;


import com.unifasservice.dto.payload.response.CartItemResponse;
import com.unifasservice.entity.CartItem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartItemConverter {

    public CartItemResponse fromEntityToDto (CartItem cartProduct) {
        CartItemResponse cartProductResponseDto = new CartItemResponse();
        cartProductResponseDto.setId(cartProduct.getId());
        cartProductResponseDto.setName(cartProduct.getProduct().getName());
//        cartProductResponseDto.setColor(cartProduct.getProduct().getVariantList());
//        cartProductResponseDto.setSize();
        cartProductResponseDto.setPrice(cartProduct.getPrice());
        cartProductResponseDto.setQuantity(cartProduct.getQuantity());
        cartProductResponseDto.setSubtotal(cartProduct.getSubtotal());
        return cartProductResponseDto;
    }

    public List<CartItemResponse> fromListEntityToDto (List<CartItem> cartProductList) {
        List<CartItemResponse> cartProductResponseDtoList = new ArrayList<>();
        for (CartItem cartProduct : cartProductList) {
            cartProductResponseDtoList.add(fromEntityToDto(cartProduct));
        }

        return cartProductResponseDtoList;
    }

}
