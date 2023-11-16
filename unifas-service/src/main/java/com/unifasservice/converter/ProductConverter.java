package com.unifasservice.converter;

import com.unifasservice.dto.response.ProductResponseDTO;
import com.unifasservice.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
    public ProductResponseDTO productToProductResponseDTO(Product product) {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setId(product.getId());
        productResponseDTO.setName(product.getName());
        productResponseDTO.setPrice(product.getPrice());
        productResponseDTO.setStock(product.getStock());
        productResponseDTO.setDescription(product.getDescription());
        productResponseDTO.setStar(product.getStar());
        return productResponseDTO;
    }
}
