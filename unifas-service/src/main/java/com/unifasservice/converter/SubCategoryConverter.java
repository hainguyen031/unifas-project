package com.unifasservice.converter;

import com.unifasservice.dto.payload.response.SubCategoryResponse;
import com.unifasservice.entity.SubCategory;
import org.springframework.stereotype.Component;

@Component
public class SubCategoryConverter {
    public SubCategoryResponse subCategoryToSubCategoryResponseDTO(SubCategory subCategory) {
        SubCategoryResponse subCategoryResponseDTO = new SubCategoryResponse();
        subCategoryResponseDTO.setId(subCategory.getId());
        subCategoryResponseDTO.setName(subCategory.getName());
        subCategoryResponseDTO.setCategoryId(subCategory.getCategory().getId());
        return subCategoryResponseDTO;
    }
}
