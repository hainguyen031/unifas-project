package com.unifasservice.converter;

import com.unifasservice.dto.payload.response.CategoryResponse;
import com.unifasservice.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {
    public CategoryResponse categoryToCategoryResponseDTO(Category category) {
        CategoryResponse categoryResponseDTO = new CategoryResponse();
        categoryResponseDTO.setId(category.getId());
        categoryResponseDTO.setName(category.getName());
        categoryResponseDTO.setGender(category.getGender());
        return categoryResponseDTO;
    }
}
