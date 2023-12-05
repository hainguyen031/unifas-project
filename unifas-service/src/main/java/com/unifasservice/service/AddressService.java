package com.unifasservice.service;


import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.dto.payload.request.AddressRequest;
import org.springframework.http.HttpStatus;

public interface AddressService {
    CommonResponse createCommonResponse(Object data, String message, HttpStatus statusCode);
    CommonResponse getUserAddressList(long userId);

    CommonResponse addNewAddress(long userId, AddressRequest addressRequest);

    CommonResponse getUserAddressForEdit(long addressId);

    CommonResponse updateAddress(long userId, long addressId, AddressRequest addressRequest);
}
