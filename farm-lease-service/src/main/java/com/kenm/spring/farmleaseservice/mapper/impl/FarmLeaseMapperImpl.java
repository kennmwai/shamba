/**
 *
 */
package com.kenm.spring.farmleaseservice.mapper.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.kenm.spring.farmleaseservice.dto.FarmLeaseDTO;
import com.kenm.spring.farmleaseservice.dto.FarmPaymentDTO;
import com.kenm.spring.farmleaseservice.entity.FarmLease;
import com.kenm.spring.farmleaseservice.entity.FarmPayment;
import com.kenm.spring.farmleaseservice.mapper.FarmLeaseMapper;

/**
 * @author User
 *
 */
public class FarmLeaseMapperImpl implements FarmLeaseMapper {

	@Override
	public FarmLeaseDTO toFarmLeaseDTO(FarmLease farmLease) {
		List<FarmPayment> payments = farmLease.getPayments();
		List<FarmPaymentDTO> paymentDTOs = mapPaymentsToDTOs(payments);

		FarmLeaseDTO farmLeaseDTO = new FarmLeaseDTO();
		BeanUtils.copyProperties(farmLease, farmLeaseDTO);
		farmLeaseDTO.setPayments(paymentDTOs);

		return farmLeaseDTO;
	}

	@Override
	public FarmLease toFarmLease(FarmLeaseDTO farmLeaseDTO) {
		List<FarmPaymentDTO> paymentDTOs = farmLeaseDTO.getPayments();
		List<FarmPayment> payments = mapDTOsToPayments(paymentDTOs);

		FarmLease farmLease = new FarmLease();
		BeanUtils.copyProperties(farmLeaseDTO, farmLease);
		farmLease.setPayments(payments);

		return farmLease;
	}

	private List<FarmPaymentDTO> mapPaymentsToDTOs(List<FarmPayment> payments) {
        return payments.stream()
                .map(payment -> {
                    FarmPaymentDTO paymentDTO = new FarmPaymentDTO();
                    BeanUtils.copyProperties(payment, paymentDTO);
                    return paymentDTO;
                })
                .collect(Collectors.toList());
    }

	private List<FarmPayment> mapDTOsToPayments(List<FarmPaymentDTO> paymentDTOs) {
        return paymentDTOs.stream()
                .map(paymentDTO -> {
                    FarmPayment payment = new FarmPayment();
                    BeanUtils.copyProperties(paymentDTO, payment);
                    return payment;
                })
                .collect(Collectors.toList());
    }
	
}
