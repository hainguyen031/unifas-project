package com.unifasservice.service;


import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.dto.payload.request.AddProductToCartRequest;
import com.unifasservice.dto.payload.response.*;

import java.util.List;

public interface CartService {
    CommonResponse addToCart(String username, AddProductToCartRequest addProduct);

    CommonResponse getCartProducts(String username);

    CommonResponse updateCartProduct(String username, long cartProductId, int newQuantity);

    DeleteCartItemResponse deleteCartProduct(String username, long cartProductId);
}
