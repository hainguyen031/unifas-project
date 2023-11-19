package com.unifasservice.converter;

import com.unifasservice.dto.payload.response.ProductResponse;
import com.unifasservice.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
    public ProductResponse productToProductResponseDTO(Product product) {
        ProductResponse productResponseDTO = new ProductResponse();
        productResponseDTO.setId(product.getId());
        productResponseDTO.setName(product.getName());
        productResponseDTO.setPrice(product.getPrice());
        productResponseDTO.setStock(product.getStock());
        productResponseDTO.setDescription(product.getDescription());
        productResponseDTO.setStar(product.getStar());
        return productResponseDTO;
    }
}
