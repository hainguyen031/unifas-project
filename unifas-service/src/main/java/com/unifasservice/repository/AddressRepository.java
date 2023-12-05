package com.unifasservice.repository;

import com.unifasservice.entity.Address;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AddressRepository  extends JpaRepository<Address, Long> {
    List<Address> findByUserId(long userId);
}
