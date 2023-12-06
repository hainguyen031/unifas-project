package com.unifasservice.service.impl;

import com.unifasservice.converter.CategoryConverter;
import com.unifasservice.converter.CategoryResponseConverter;
import com.unifasservice.dto.payload.request.AddCategoryRequest;
import com.unifasservice.dto.payload.response.CategoryResponse;
import com.unifasservice.dto.payload.response.PageResponse;
import com.unifasservice.entity.Category;
import com.unifasservice.repository.CategoryRepository;
import com.unifasservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryConverter categoryConverter;

    @Autowired
    private CategoryResponseConverter categoryResponseConverter;

    @Override
    public List<CategoryResponse> findAll() {
        List<CategoryResponse> categoriesResponseDTO = new ArrayList<>();
        List<Category> categories = categoryRepository.findAll();
        for (Category category : categories) {
            CategoryResponse categoryResponseDTO =
                    categoryConverter.categoryToCategoryResponseDTO(category);
            categoriesResponseDTO.add(categoryResponseDTO);
        }
        return categoriesResponseDTO;
    }

    @Override
    public PageResponse<CategoryResponse> findAllPageable(Pageable pageableRequest) {
        Page<Category> categories = categoryRepository.findAll(pageableRequest);
        PageResponse<CategoryResponse> categoryResponsePageResponse = new PageResponse<>();
        categoryResponsePageResponse.setContent(categoryResponseConverter.convert(categories.getContent()));
        categoryResponsePageResponse.setPageSize(categories.getSize());
        categoryResponsePageResponse.setTotalPages(categories.getTotalPages());
        categoryResponsePageResponse.setHasNext(categories.hasNext());
        categoryResponsePageResponse.setHasPrevious(categories.hasPrevious());
        categoryResponsePageResponse.setTotalElements(categories.getTotalElements());
        categoryResponsePageResponse.setCurrentPageNumber(categories.getNumber());
        return categoryResponsePageResponse;
    }

    @Override
    public Boolean addCategory(AddCategoryRequest addCategoryRequest) {
        Category checkCategory = categoryRepository.findByNameAndGender(addCategoryRequest.getName(), addCategoryRequest.getGender());
        if (checkCategory == null) {
            Category category = new Category(addCategoryRequest.getName(), addCategoryRequest.getGender(), true);
            categoryRepository.save(category);
            return true;
        }
        return false;
    }
}
