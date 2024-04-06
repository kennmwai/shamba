package com.kenm.spring.farmleaseservice.service;

import java.util.List;

import org.springframework.lang.NonNull;

import com.kenm.spring.farmleaseservice.dto.FarmPaymentDTO;
import com.kenm.spring.farmleaseservice.entity.FarmLease;

public interface FarmPaymentService {

    long count();

    boolean exists(@NonNull Long id);

    void deleteAll();

    void delete(@NonNull Long id);

    void deleteByIds(@NonNull Iterable<Long> ids);

    List<FarmPaymentDTO> getAllPayments();

    FarmPaymentDTO getPayment(@NonNull Long id);

    List<FarmPaymentDTO> getPaymentsByIds(@NonNull Iterable<Long> ids);

    List<FarmPaymentDTO> getFarmPaymentByStatus(String status);

    List<FarmPaymentDTO> getFarmPaymentByMethod(String method);

    List<FarmPaymentDTO> getFarmPaymentByReceipt(Long receipt);

    FarmPaymentDTO createPayment(FarmPaymentDTO farmPaymentDTO, FarmLease lease);

    FarmPaymentDTO updatePayment(FarmPaymentDTO farmPaymentDTO, FarmLease lease);

    List<FarmPaymentDTO> updatePayments(List<FarmPaymentDTO> farmPaymentDTOs, FarmLease lease);

}
