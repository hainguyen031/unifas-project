package com.unifasservice.controller;

import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.dto.payload.request.AddressRequest;
import com.unifasservice.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
@CrossOrigin("*")
public class AddressController {

    private final AddressService addressService;

    @GetMapping("/addresses/{user-id}")
    private ResponseEntity<CommonResponse> getUserAddressList(@PathVariable("user-id") long userId) {
        try {
        CommonResponse commonResponse = addressService.getUserAddressList(userId);
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
        }catch (Exception e){
            CommonResponse commonResponse = createCommonResponse(false, e.getMessage(), HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(commonResponse, HttpStatus.OK);
        }
    }

    @GetMapping("/address/{address-id}")
    private ResponseEntity<CommonResponse> getUserAddressForEdit(@PathVariable("address-id") long addressId) {
        try {
            CommonResponse commonResponse = addressService.getUserAddressForEdit(addressId);
            return new ResponseEntity<>(commonResponse, HttpStatus.OK);
        } catch (Exception e) {
            CommonResponse commonResponse = createCommonResponse(false, e.getMessage(), HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(commonResponse, HttpStatus.OK);
        }
    }


    @PostMapping("/address/{user-id}")
    private ResponseEntity<CommonResponse> addNewAddress(@PathVariable("user-id") long userId, @RequestBody AddressRequest addressRequest) {
        try {
            CommonResponse commonResponse = addressService.addNewAddress(userId, addressRequest);
            return new ResponseEntity<>(commonResponse, HttpStatus.OK);
        } catch (Exception e) {
            CommonResponse commonResponse = createCommonResponse(false, e.getMessage(), HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(commonResponse, HttpStatus.OK);
        }

    }

    @PutMapping("/user/{user-id}/address/{address-id}")
    private ResponseEntity<CommonResponse> updateAddress(@PathVariable("user-id") long userId, @PathVariable("address-id") long addressId, @RequestBody AddressRequest addressRequest){
        try {
            CommonResponse commonResponse = addressService.updateAddress(userId, addressId, addressRequest);
            return new ResponseEntity<>(commonResponse, HttpStatus.OK);
        } catch (Exception e) {
            CommonResponse commonResponse = createCommonResponse(false, e.getMessage(), HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(commonResponse, HttpStatus.OK);
        }
    }


    public CommonResponse createCommonResponse(Object data, String message, HttpStatus statusCode) {
        return CommonResponse
                .builder()
                .data(data)
                .message(message)
                .statusCode(statusCode)
                .build();
    }

}
