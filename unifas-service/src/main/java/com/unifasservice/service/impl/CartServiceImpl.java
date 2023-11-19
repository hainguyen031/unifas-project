package com.unifasservice.service.impl;

import com.unifasservice.converter.CartItemConverter;
import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.dto.payload.request.AddProductToCartRequest;
import com.unifasservice.dto.payload.response.*;
import com.unifasservice.entity.*;
import com.unifasservice.repository.*;
import com.unifasservice.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    private final CartRepository cartRepository;

    private final VariantRepository variantRepository;

    private final CartItemConverter cartProductConverter;

    private final CartProductRepository cartProductRepository;

    @Override
    public CommonResponse addToCart(String username, AddProductToCartRequest addProduct) {

        CommonResponse commonResponse = new CommonResponse();

        AddProductToCartResponse addProductToCartResponse = new AddProductToCartResponse();


        User user = userRepository.findByUsername(username);
        if (user == null) {

            commonResponse.builder()
                    .message("User not found !")
                    .statusCode(HttpStatus.BAD_REQUEST)
                    .data(null)
                    .build();
            return commonResponse;
        }


        long productId = addProduct.getProductId();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product == null) {

            commonResponse.builder()
                    .message("Product not found !")
                    .statusCode(HttpStatus.BAD_REQUEST)
                    .data(null)
                    .build();
            return commonResponse;


        }

        long variantId = addProduct.getVariantId();
        Variant variant = variantRepository.findById(variantId)
                .orElseThrow(() -> new RuntimeException("Variant not found"));

        if (product.getVariantList() == null) {
            product.setVariantList(new ArrayList<>());
        }

        variant.setProduct(product);
        product.getVariantList().add(variant);

        productRepository.save(product);


        Cart cart = user.getCart();
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            user.setCart(cart);
        }

        CartItem cartProduct = new CartItem();
        cartProduct.setProduct(product);
        cartProduct.setCart(cart);
        cartRepository.save(cart);


        addProductToCartResponse.setId(product.getId());
        addProductToCartResponse.setQuantity(addProduct.getQuantity());
        addProductToCartResponse.setPrice(product.getPrice());
        addProductToCartResponse.setSubtotal(addProduct.getQuantity() * product.getPrice());


        commonResponse.builder()
                .message("Product added to cart successfully !")
                .statusCode(HttpStatus.OK)
                .data(addProductToCartResponse)
                .build();
        return commonResponse;

      }

    @Override
    public CommonResponse getCartProducts(String username) {

        CommonResponse commonResponse = new CommonResponse();

        User user = userRepository.findByUsername(username);
        if (user == null || user.getCart() == null) {

            commonResponse.builder()
                    .message("User not found or Cart not created ! ")
                    .statusCode(HttpStatus.BAD_REQUEST)
                    .data(null)
                    .build();
            return commonResponse;
        }

        Cart cart = user.getCart();
        List<CartItem> cartProductList = cart.getCartProductList();

        List<CartItemResponse> cartProductResponseDtoList = cartProductConverter.fromListEntityToDto(cartProductList);


        commonResponse.builder()
                .message("Show successfully !")
                .statusCode(HttpStatus.OK)
                .data(cartProductResponseDtoList)
                .build();
        return commonResponse;


    }

    @Override
    public CommonResponse updateCartProduct(String username, long cartProductId, int newQuantity) {

        CommonResponse commonResponse = new CommonResponse();

        UpdateCartItemResponse responseDto = new UpdateCartItemResponse();

        User user = userRepository.findByUsername(username);

        if (user == null || user.getCart() == null) {

            commonResponse.builder()
                    .message("User not found or Cart not created ! ")
                    .statusCode(HttpStatus.BAD_REQUEST)
                    .data(null)
                    .build();
            return commonResponse;


        }

        Cart cart = user.getCart();


        for (CartItem cartProduct : cart.getCartProductList()) {
            if (cartProduct.getId() == cartProductId) {

                cartProduct.setQuantity(newQuantity);
                cartProduct.setSubtotal(newQuantity*cartProduct.getPrice());
                cartProductRepository.save(cartProduct);

                responseDto.setId(cartProduct.getId());
                responseDto.setName(cartProduct.getProduct().getName());
                responseDto.setPrice(cartProduct.getPrice());
                responseDto.setQuantity(cartProduct.getQuantity());
                responseDto.setSubtotal(cartProduct.getSubtotal());


                commonResponse.builder()
                        .message("Cart Item updated successfully !")
                        .statusCode(HttpStatus.OK)
                        .data(responseDto)
                        .build();
                return commonResponse;
            }
        }

        commonResponse.builder()
                .message("Cart Item not found !")
                .statusCode(HttpStatus.BAD_REQUEST)
                .data(null)
                .build();
        return commonResponse;
    }

    @Override
    public DeleteCartItemResponse deleteCartProduct(String username, long cartProductId) {
        DeleteCartItemResponse responseDto = new DeleteCartItemResponse();

        User user = userRepository.findByUsername(username);
        if (user == null || user.getCart() == null) {
            responseDto.setMessage("User or cart not found");
            responseDto.setSuccess(false);
            return responseDto;
        }

        Cart cart = user.getCart();


        for (CartItem cartProduct : cart.getCartProductList()) {
            if (cartProduct.getId() == cartProductId) {

                cart.getCartProductList().remove(cartProduct);
                cartRepository.save(cart);
                responseDto.setMessage("CartProduct deleted successfully");
                responseDto.setSuccess(true);
                return responseDto;
            }
        }


        responseDto.setMessage("CartProduct not found");
        responseDto.setSuccess(false);
        return responseDto;
    }


}
