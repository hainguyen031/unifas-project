package com.unifasservice.service.impl;

import com.unifasservice.converter.AddressConverter;
import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.dto.payload.request.AddressRequest;
import com.unifasservice.dto.payload.response.AddressResponse;
import com.unifasservice.entity.Address;
import com.unifasservice.entity.Order;
import com.unifasservice.entity.User;
import com.unifasservice.repository.AddressRepository;
import com.unifasservice.repository.UserRepository;
import com.unifasservice.service.AddressService;
import com.unifasservice.utilities.ValidateAddress;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    private final AddressConverter addressConverter;


    @Override
    public CommonResponse createCommonResponse(Object data, String message, HttpStatus statusCode) {
        return CommonResponse
                .builder()
                .data(data)
                .message(message)
                .statusCode(statusCode)
                .build();
    }

    @Override
    public CommonResponse getUserAddressList(long userId) {
        List<Address> addressList = addressRepository.findByUserId(userId);
        for (int i = 0; i < addressList.size(); i++) {
            if ("T".equals(addressList.get(i).getIsDefault())) {
                Address subAddress = addressList.get(i);
                addressList.remove(addressList.get(i));
                addressList.add(0, subAddress);
                break;
            }
        }


        List<AddressResponse> addressResponseList = addressConverter.convertAddressEntityListToResponseList(addressList);

        return createCommonResponse(addressResponseList, "Get address success", HttpStatus.OK);
    }

    @Override
    public CommonResponse addNewAddress(long userId, AddressRequest addressRequest) {
        User userEntity = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        List<Address> addressList = userEntity.getAddressList();

        boolean isReceiverValid = ValidateAddress.validate("RECEIVER",addressRequest.getReceiver());
        boolean isContactValid = ValidateAddress.validate("CONTACT",addressRequest.getContact());
        boolean isStreetValid = ValidateAddress.validate("STREET",addressRequest.getStreet());

        if (!isReceiverValid){
            throw new IllegalArgumentException("Name must be 2 characters or more");
        }else if (!isContactValid){
            throw new IllegalArgumentException("Phone number must have 10 number and start with 0");
        }else if (!isStreetValid) {
            throw new IllegalArgumentException("Street must be filled");
        }

        Address address = addressConverter.convertAddressRequestToEntity(addressRequest);

        String isDefaultAddress = address.getIsDefault();
        if ("T".equals(isDefaultAddress)) {
            for (Address subAddress : addressList){
                if ("T".equals(subAddress.getIsDefault())){
                    subAddress.setIsDefault("F");
                    break;
                }
            }
            address.setUser(userEntity);
            address.setOrderList(Collections.singletonList(new Order()));
            addressRepository.save(address);
            return createCommonResponse(false, "Add new address successfully", HttpStatus.OK);
        } else {
            if (addressList.size()==0){
                address.setIsDefault("T");
            }

            address.setUser(userEntity);
            address.setOrderList(Collections.singletonList(new Order()));
            addressRepository.save(address);
            return createCommonResponse(false, "Add new address successfully", HttpStatus.OK);
        }
    }

    @Override
    public CommonResponse getUserAddressForEdit(long addressId) {
       Address address = addressRepository.findById(addressId).orElseThrow(
               () -> new IllegalArgumentException("Address not found")
       );
       AddressResponse addressResponse = addressConverter.convertAddressEntityToResponse(address);
       return createCommonResponse(addressResponse,"Get address for edit successfully", HttpStatus.OK);
    }

    @Override
    public CommonResponse updateAddress(long userId,long addressId, AddressRequest addressRequest) {
        Address addressEntity = addressRepository.findById(addressId).orElseThrow(
                () -> new IllegalArgumentException("Address not found")
        );


        boolean isReceiverValid = ValidateAddress.validate("RECEIVER",addressRequest.getReceiver());
        boolean isContactValid = ValidateAddress.validate("CONTACT",addressRequest.getContact());
        boolean isStreetValid = ValidateAddress.validate("STREET",addressRequest.getStreet());

        if (!isReceiverValid){
            throw new IllegalArgumentException("Name must be 2 characters or more");
        }else if (!isContactValid){
            throw new IllegalArgumentException("Phone number must have 10 number and start with 0");
        }else if (!isStreetValid){
            throw new IllegalArgumentException("Street must be filled");
        }else {
            addressEntity.setReceiver(addressRequest.getReceiver());
            addressEntity.setContact(addressRequest.getContact());
            addressEntity.setCity(addressRequest.getCity());
            addressEntity.setDistrict(addressRequest.getDistrict());
            addressEntity.setStreet(addressRequest.getStreet());
        }

        if ("true".equals(addressRequest.getIsDefault()) && "T".equals(addressEntity.getIsDefault()) ||
                "false".equals(addressRequest.getIsDefault()) && "F".equals(addressEntity.getIsDefault())
        ){

        }else {
            if ("true".equals(addressRequest.getIsDefault())) {
                User userEntity = userRepository.findById(userId).orElseThrow(
                        () -> new IllegalArgumentException("User not found")
                );
                List<Address> addressList = userEntity.getAddressList();
                for (Address subAddress : addressList) {
                    if ("T".equals(subAddress.getIsDefault())) {
                        subAddress.setIsDefault("F");
                        break;
                    }
                }
                addressEntity.setIsDefault("T");
            }else {
                throw new IllegalArgumentException("Can not uncheck default address");
            }
        }
        addressRepository.save(addressEntity);
        return createCommonResponse(false,"Update address successfully",HttpStatus.OK);
    }
}





