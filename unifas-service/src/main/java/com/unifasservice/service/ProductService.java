package com.unifasservice.service;

import com.unifasservice.dto.response.ProductResponseDTO;

import java.util.List;

public interface ProductService {
    List<ProductResponseDTO> findAll();
}
