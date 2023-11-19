package com.unifasservice.repository;

import com.unifasservice.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CartProductRepository  extends JpaRepository<CartItem, Long> {
}
