package com.unifasservice.controller;


import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.dto.payload.request.AddProductToCartRequest;
import com.unifasservice.dto.payload.request.UpdateCartProductRequest;
import com.unifasservice.dto.payload.response.*;
import com.unifasservice.service.CartService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/cart")
@CrossOrigin("*")
@RequiredArgsConstructor
public class CartController {


    private final CartService cartService;

    @PostMapping("/add-product")
    public ResponseEntity<CommonResponse> addToCart(
            @RequestBody AddProductToCartRequest addProduct,
            Authentication authentication) {
        try {
            String username = authentication.getName();

            CommonResponse responseDto = cartService.addToCart(username, addProduct);

            return new ResponseEntity<>(responseDto , HttpStatus.OK);

        } catch (Exception e) {
            CommonResponse errorResponse = new CommonResponse();
            errorResponse.setMessage("Error adding product to cart");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/cart-products")
    public ResponseEntity<CommonResponse> getCartProducts(Authentication authentication) {

            String username = authentication.getName();
            CommonResponse cartProducts = cartService.getCartProducts(username);
            return new ResponseEntity<>(cartProducts , HttpStatus.OK);

    }


    @PutMapping("/{cartProductId}")
    public ResponseEntity<CommonResponse> updateCartProduct(
            @PathVariable("cartProductId") long cartProductId,
            @RequestBody UpdateCartProductRequest updateRequest,
            Authentication authentication) {
        try {
            String username = authentication.getName();
            int newQuantity = updateRequest.getNewQuantity();
            CommonResponse responseDto = cartService.updateCartProduct(username, cartProductId, newQuantity);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (Exception e) {
            return (ResponseEntity<CommonResponse>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/{cartProductId}")
    public ResponseEntity<DeleteCartItemResponse> deleteCartProduct(
            @PathVariable("cartProductId") long cartProductId,
            Authentication authentication) {
        try {
            String username = authentication.getName();
            DeleteCartItemResponse responseDto = cartService.deleteCartProduct(username, cartProductId);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (Exception e) {
            return (ResponseEntity<DeleteCartItemResponse>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);        }
    }


}
