package com.kenm.spring.farmleaseservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kenm.spring.farmleaseservice.entity.FarmPayment;

public interface FarmPaymentRepository extends JpaRepository<FarmPayment, Long> {

    List<FarmPayment> findByStatus(String status);

    List<FarmPayment> findByMethod(String method);

    List<FarmPayment> findByReceipt(Long receipt);

}
