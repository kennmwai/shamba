package com.kenm.spring.farmleaseservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kenm.spring.farmleaseservice.entity.FarmPayment;

public interface FarmPaymentRepository extends JpaRepository<FarmPayment, Long> {

    @Query("SELECT f FROM FarmPayment f WHERE f.paymentStatus = ?1")
    List<FarmPayment> findByStatus(String status);

    @Query("SELECT f FROM FarmPayment f WHERE f.paymentMethod = ?1")
    List<FarmPayment> findByMethod(String method);

    @Query("SELECT f FROM FarmPayment f WHERE f.paymentReceipt = ?1")
    List<FarmPayment> findByReceipt(Long receipt);

}
