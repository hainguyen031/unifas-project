package com.unifasservice.repository;

import com.unifasservice.entity.ImageProduct;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ImageProductRepository  extends JpaRepository<ImageProduct, Long> {
}
