package com.unifasservice.repository;

import com.unifasservice.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CartRepository  extends JpaRepository<Cart, Long> {
}
