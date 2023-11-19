package com.unifasservice.controller;

import com.unifasservice.dto.payload.response.SubCategoryResponse;
import com.unifasservice.service.impl.SubCategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/subCategories")
@CrossOrigin("*")
public class SubCategoryController {
    @Autowired
    private SubCategoryServiceImpl subCategoryService;

    @GetMapping()
    private ResponseEntity<List<SubCategoryResponse>> getAllSubCategory() {
        List<SubCategoryResponse> subCategories = subCategoryService.findAll();
        return new ResponseEntity<>(subCategories, HttpStatus.OK);
    }

}
