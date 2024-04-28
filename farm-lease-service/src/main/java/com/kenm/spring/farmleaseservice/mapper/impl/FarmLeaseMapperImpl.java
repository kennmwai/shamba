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

	private List<FarmPayment> mapDTOsToPayments(List<FarmPaymentDTO> paymentDTOs) {

		List<FarmPayment> payments = paymentDTOs.stream()
				.map(paymentDTO -> {
					if (paymentDTO == null) {
						return null;
					}
					FarmPayment payment = new FarmPayment();
					BeanUtils.copyProperties(paymentDTO, payment);
					return payment;
				})
				.collect(Collectors.toList());

		return payments;
	}

	private List<FarmPaymentDTO> mapPaymentsToDTOs(List<FarmPayment> payments) {
        return payments.stream()
                .map(payment -> {
					if (payment == null) {
						return null;
					}
                    FarmPaymentDTO paymentDTO = new FarmPaymentDTO();
                    BeanUtils.copyProperties(payment, paymentDTO);
                    return paymentDTO;
                })
                .collect(Collectors.toList());
    }

	@Override
	public FarmLease toFarmLease(FarmLeaseDTO farmLeaseDTO) {
		List<FarmPayment> payments = mapDTOsToPayments(farmLeaseDTO.getPayments());
		FarmLease farmLease = new FarmLease();

		BeanUtils.copyProperties(farmLeaseDTO, farmLease);
		farmLease.setPayments(payments);

		return farmLease;
	}

	@Override
	public FarmLeaseDTO toFarmLeaseDTO(FarmLease farmLease) {
		List<FarmPaymentDTO> paymentDTOs = mapPaymentsToDTOs(farmLease.getPayments());
		FarmLeaseDTO farmLeaseDTO = new FarmLeaseDTO();

		BeanUtils.copyProperties(farmLease, farmLeaseDTO);
		farmLeaseDTO.setPayments(paymentDTOs);

		return farmLeaseDTO;
	}
}
