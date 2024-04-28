package com.kenm.spring.farmleaseservice.service;

import java.util.List;

import com.kenm.spring.farmleaseservice.dto.FarmPaymentDTO;
import com.kenm.spring.farmleaseservice.entity.FarmLease;

public interface FarmPaymentService {

    long count();

    boolean exists(Long id);

    void deleteAll();

    void delete(Long id);

    void deleteByIds(Iterable<Long> ids);

    List<FarmPaymentDTO> getAllPayments();

    FarmPaymentDTO getPayment(Long id);

    List<FarmPaymentDTO> getPaymentsByIds(Iterable<Long> ids);

    List<FarmPaymentDTO> getFarmPaymentByStatus(String status);

    List<FarmPaymentDTO> getFarmPaymentByMethod(String method);

    List<FarmPaymentDTO> getFarmPaymentByReceipt(Long receipt);

    FarmPaymentDTO createPayment(FarmPaymentDTO farmPaymentDTO, FarmLease lease);

    FarmPaymentDTO updatePayment(FarmPaymentDTO farmPaymentDTO, FarmLease lease);

}
