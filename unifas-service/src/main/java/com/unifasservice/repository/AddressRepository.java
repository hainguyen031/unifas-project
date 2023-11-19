package com.unifasservice.repository;

import com.unifasservice.entity.Address;

import org.springframework.data.jpa.repository.JpaRepository;



public interface AddressRepository  extends JpaRepository<Address, Long> {
}
