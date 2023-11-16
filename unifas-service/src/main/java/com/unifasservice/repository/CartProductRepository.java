package com.unifasservice.repository;

import com.unifasservice.entity.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CartProductRepository  extends JpaRepository<CartProduct, Long> {
}
