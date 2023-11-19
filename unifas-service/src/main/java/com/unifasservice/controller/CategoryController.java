package com.unifasservice.controller;

import com.unifasservice.dto.payload.response.CategoryResponse;
import com.unifasservice.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
