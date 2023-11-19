package com.unifasservice.service;

import com.unifasservice.dto.payload.response.SubCategoryResponse;

import java.util.List;
public interface SubCategoryService {
    List<SubCategoryResponse> findAll();
}
