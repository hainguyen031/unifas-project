package com.unifasservice.dto.payload.response;

import lombok.Data;

@Data
public class ProductResponse {
    private long id;
    private String name;
    private double price;
    private int stock;
    private String description;
    private int star;
}
