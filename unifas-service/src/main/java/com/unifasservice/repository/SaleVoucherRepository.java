package com.unifasservice.repository;


import com.unifasservice.entity.SaleVoucher;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SaleVoucherRepository  extends JpaRepository<SaleVoucher, Long> {
    SaleVoucher findByCode(String code);
}
