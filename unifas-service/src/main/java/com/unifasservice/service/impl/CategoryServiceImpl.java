package com.unifasservice.service.impl;

import com.unifasservice.converter.CategoryConverter;
import com.unifasservice.dto.response.CategoryResponseDTO;
import com.unifasservice.entity.Category;
import com.unifasservice.repository.CategoryRepository;
import com.unifasservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryConverter categoryConverter;

    @Override
    public List<CategoryResponseDTO> findAll() {
        List<CategoryResponseDTO> categoriesResponseDTO = new ArrayList<>();
        List<Category> categories = categoryRepository.findAll();
        for(Category category : categories) {
                CategoryResponseDTO categoryResponseDTO =
                        categoryConverter.categoryToCategoryResponseDTO(category);
                categoriesResponseDTO.add(categoryResponseDTO);
        }
        return categoriesResponseDTO;
    }
}
