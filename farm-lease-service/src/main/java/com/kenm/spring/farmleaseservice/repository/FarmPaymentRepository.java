package com.kenm.spring.farmleaseservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kenm.spring.farmleaseservice.entity.FarmPayment;

public interface FarmPaymentRepository extends JpaRepository<FarmPayment, Long> {

}
