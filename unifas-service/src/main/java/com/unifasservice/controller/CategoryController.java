package com.unifasservice.controller;

import com.unifasservice.dto.payload.request.AddCategoryRequest;
import com.unifasservice.dto.payload.request.UpdataCategoryRequest;
import com.unifasservice.dto.payload.response.CategoryResponse;
import com.unifasservice.dto.payload.response.PageResponse;
import com.unifasservice.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
@CrossOrigin("*")
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;

    @GetMapping()
    public ResponseEntity<List<CategoryResponse>> getAllCategory() {
        List<CategoryResponse> categories = categoryService.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> getAllCategoryPageable(Pageable pageable) {
        Pageable pageableRequest = PageRequest.of(pageable.getPageNumber(), 15, Sort.by(Sort.Order.asc("id")));
        PageResponse<CategoryResponse> categories = categoryService.findAllPageable(pageableRequest);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addNewCategory(@RequestBody AddCategoryRequest addCategoryRequest) {
        Boolean result = categoryService.addCategory(addCategoryRequest);
        if (result) {
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(false, HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long categoryId) {
        Boolean result = categoryService.deleteCategory(categoryId);
        if (result) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateCategory(@RequestBody UpdataCategoryRequest updataCategoryRequest) {
        Boolean result = categoryService.updateCategory(updataCategoryRequest);
        if (result) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }
}
