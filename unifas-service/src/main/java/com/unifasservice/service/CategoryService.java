package com.unifasservice.service;


import com.unifasservice.dto.payload.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> findAll();
}
