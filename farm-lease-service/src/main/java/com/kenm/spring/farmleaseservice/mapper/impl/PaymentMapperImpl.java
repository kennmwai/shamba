package com.kenm.spring.farmleaseservice.mapper.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.kenm.spring.farmleaseservice.dto.FarmPaymentDTO;
import com.kenm.spring.farmleaseservice.entity.FarmPayment;
import com.kenm.spring.farmleaseservice.mapper.PaymentMapper;

@Component
public class PaymentMapperImpl implements PaymentMapper {

    @Override
    public FarmPayment toFarmPayment(FarmPaymentDTO farmPaymentDTO) {
        if (farmPaymentDTO == null) {
            return null;
        }
        FarmPayment farmPayment = new FarmPayment();
        BeanUtils.copyProperties(farmPaymentDTO, farmPayment);
        return farmPayment;
    }

    @Override
    public FarmPaymentDTO toFarmPaymentDTO(FarmPayment farmPayment) {
        if (farmPayment == null) {
            return null;
        }
        FarmPaymentDTO farmPaymentDTO = new FarmPaymentDTO();
        BeanUtils.copyProperties(farmPayment, farmPaymentDTO);
        return farmPaymentDTO;
    }

    @Override
    public List<FarmPaymentDTO> toFarmPaymentDTOs(List<FarmPayment> payments) {
        if (payments == null || payments.isEmpty()) {
            return Collections.emptyList();
        }

        List<FarmPaymentDTO> paymentDTOs = new ArrayList<>(payments.size());
        for (FarmPayment payment : payments) {
            paymentDTOs.add(toFarmPaymentDTO(payment));
        }
        return paymentDTOs;
    }

    @Override
    public List<FarmPayment> toFarmPayments(List<FarmPaymentDTO> paymentDTOs) {
        if (paymentDTOs == null || paymentDTOs.isEmpty()) {
            return Collections.emptyList();
        }
        List<FarmPayment> payments = new ArrayList<>(paymentDTOs.size());
        for (FarmPaymentDTO dto : paymentDTOs) {
            payments.add(toFarmPayment(dto));
        }
        return payments;
    }

}
