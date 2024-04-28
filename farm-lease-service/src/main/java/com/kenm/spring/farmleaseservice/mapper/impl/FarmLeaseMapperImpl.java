/**
 *
 */
package com.kenm.spring.farmleaseservice.mapper.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kenm.spring.farmleaseservice.dto.FarmLeaseDTO;
import com.kenm.spring.farmleaseservice.dto.FarmPaymentDTO;
import com.kenm.spring.farmleaseservice.dto.payload.CreateLeaseRequest;
import com.kenm.spring.farmleaseservice.dto.payload.UpdateLeaseRequest;
import com.kenm.spring.farmleaseservice.entity.FarmLease;
import com.kenm.spring.farmleaseservice.entity.FarmPayment;
import com.kenm.spring.farmleaseservice.mapper.FarmLeaseMapper;
import com.kenm.spring.farmleaseservice.mapper.PaymentMapper;

/**
 * @author User
 *
 */
@Component
public class FarmLeaseMapperImpl implements FarmLeaseMapper {

	@Autowired
	private PaymentMapper paymentMapper;

	@Override
	public FarmLease toFarmLease(FarmLeaseDTO farmLeaseDTO) {
		List<FarmPayment> payments = paymentMapper.toFarmPayments(farmLeaseDTO.getPayments());
		FarmLease farmLease = new FarmLease();

		BeanUtils.copyProperties(farmLeaseDTO, farmLease);
		farmLease.setPayments(payments);

		return farmLease;
	}

	@Override
	public FarmLeaseDTO toFarmLeaseDTO(FarmLease farmLease) {
		List<FarmPaymentDTO> paymentDTOs = paymentMapper.toFarmPaymentDTOs(farmLease.getPayments());
		FarmLeaseDTO farmLeaseDTO = new FarmLeaseDTO();

		BeanUtils.copyProperties(farmLease, farmLeaseDTO);
		farmLeaseDTO.setPayments(paymentDTOs);

		return farmLeaseDTO;
	}

	@Override
	public List<FarmLeaseDTO> toFarmLeaseDTOs(List<FarmLease> farmLeases) {
		if (farmLeases == null) {
			return null;
		}
		return farmLeases.stream().map(this::toFarmLeaseDTO).collect(Collectors.toList());
	}

	@Override
	public List<FarmLease> toFarmLeases(List<FarmLeaseDTO> farmLeaseDTOs) {
		if (farmLeaseDTOs == null) {
			return null;
		}
		return farmLeaseDTOs.stream().map(this::toFarmLease).collect(Collectors.toList());
	}

	@Override
	public FarmLease toFarmLease(CreateLeaseRequest createLeaseDTO) {
		FarmLease farmLease = new FarmLease();

		BeanUtils.copyProperties(createLeaseDTO, farmLease);

		return farmLease;
	}

	@Override
	public FarmLease toFarmLease(UpdateLeaseRequest updateLeaseDTO) {
		FarmLease farmLease = new FarmLease();

		BeanUtils.copyProperties(updateLeaseDTO, farmLease);

		return farmLease;
	}
}
