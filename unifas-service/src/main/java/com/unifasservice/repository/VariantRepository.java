package com.unifasservice.repository;

import com.unifasservice.entity.Variant;
import org.springframework.data.jpa.repository.JpaRepository;



public interface VariantRepository  extends JpaRepository<Variant, Long> {
}
