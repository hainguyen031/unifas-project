package com.unifasservice.converter;

import com.unifasservice.dto.payload.request.AddressRequest;
import com.unifasservice.dto.payload.response.AddressResponse;
import com.unifasservice.entity.Address;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AddressConverter {

    public AddressResponse convertAddressEntityToResponse(Address address){
        AddressResponse addressResponse = new AddressResponse();
        BeanUtils.copyProperties(address,addressResponse);
        if("T".equals(address.getIsDefault())){
            addressResponse.setIsDefault("true");
        }else {
            addressResponse.setIsDefault("false");
        }
        return addressResponse;
    }

    public List<AddressResponse> convertAddressEntityListToResponseList (List<Address> addressList){
        return addressList.stream().map(this::convertAddressEntityToResponse).collect(Collectors.toList());
    }

    public Address convertAddressRequestToEntity(AddressRequest addressRequest) {
        Address address = new Address();
        BeanUtils.copyProperties(addressRequest,address);
        if ("false".equals(addressRequest.getIsDefault())){
            address.setIsDefault("F");
        }else {
            address.setIsDefault("T");
        }
        return address;
    }
}
