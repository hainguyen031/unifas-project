package com.unifasservice.repository;

import com.unifasservice.entity.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderLineRepository  extends JpaRepository<OrderLine, Long> {
}
