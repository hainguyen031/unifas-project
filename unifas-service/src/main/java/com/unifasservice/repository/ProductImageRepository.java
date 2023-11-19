package com.unifasservice.repository;

import com.unifasservice.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
}
