package com.unifasservice.dto.payload.response;

import lombok.Data;

@Data
public class SubCategoryResponse {
    private long id;
    private String name;
    private long categoryId;
}
