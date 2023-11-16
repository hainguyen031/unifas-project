package com.unifasservice.converter;

import com.unifasservice.dto.response.SubCategoryResponseDTO;
import com.unifasservice.entity.SubCategory;
import org.springframework.stereotype.Component;

@Component
public class SubCategoryConverter {
    public SubCategoryResponseDTO subCategoryToSubCategoryResponseDTO(SubCategory subCategory) {
        SubCategoryResponseDTO subCategoryResponseDTO = new SubCategoryResponseDTO();
        subCategoryResponseDTO.setId(subCategory.getId());
        subCategoryResponseDTO.setName(subCategory.getName());
        subCategoryResponseDTO.setCategoryId(subCategory.getCategory().getId());
        return subCategoryResponseDTO;
    }
}
