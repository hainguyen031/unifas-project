package com.unifasservice.dto.payload.request;

import lombok.Data;

@Data
public class UpdataCategoryRequest {
    private long id;
    private String name;
    private String gender;
}
