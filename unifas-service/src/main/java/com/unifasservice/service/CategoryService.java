package com.unifasservice.service;


import com.unifasservice.dto.payload.request.AddCategoryRequest;
import com.unifasservice.dto.payload.request.UpdataCategoryRequest;
import com.unifasservice.dto.payload.response.CategoryResponse;
import com.unifasservice.dto.payload.response.PageResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> findAll();

    PageResponse<CategoryResponse> findAllPageable(Pageable pageableRequest);

    Boolean addCategory(AddCategoryRequest addCategoryRequest);

    Boolean deleteCategory(Long categoryId);

    Boolean updateCategory(UpdataCategoryRequest updataCategoryRequest);

}
