package com.kenm.spring.farmleaseservice.mapper;

import java.util.List;

import com.kenm.spring.farmleaseservice.dto.FarmPaymentDTO;
import com.kenm.spring.farmleaseservice.entity.FarmPayment;


public interface PaymentMapper {

    FarmPayment toFarmPayment(FarmPaymentDTO farmPaymentDTO);

    FarmPaymentDTO toFarmPaymentDTO(FarmPayment farmPayment);

    List<FarmPaymentDTO> toFarmPaymentDTOs(List<FarmPayment> payments);

    List<FarmPayment> toFarmPayments(List<FarmPaymentDTO> paymentDTOs);

}
