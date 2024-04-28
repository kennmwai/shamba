/**
 *
 */
package com.kenm.spring.farmleaseservice.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kenm.spring.farmleaseservice.dto.FarmLeaseDTO;
import com.kenm.spring.farmleaseservice.entity.FarmLease;
import com.kenm.spring.farmleaseservice.entity.FarmPayment;
import com.kenm.spring.farmleaseservice.exception.ResourceNotFoundException;
import com.kenm.spring.farmleaseservice.mapper.FarmLeaseMapper;
import com.kenm.spring.farmleaseservice.mapper.PaymentMapper;
import com.kenm.spring.farmleaseservice.repository.FarmLeaseRepository;
import com.kenm.spring.farmleaseservice.repository.FarmPaymentRepository;
import com.kenm.spring.farmleaseservice.service.FarmLeaseService;

/**
 * @author User
 *
 */
@Service
public class FarmLeaseServiceImpl implements FarmLeaseService {

	@Autowired
	private FarmLeaseRepository farmLeaseRepository;

	@Autowired
	private FarmPaymentRepository paymentRepository;

	@Autowired
	private FarmLeaseMapper farmLeaseMapper;

	@Autowired
	private PaymentMapper paymentMapper;

	@Override
	public long count() {
		return farmLeaseRepository.count();
	}

	@Override
	public boolean exists(Long id) {
		return farmLeaseRepository.existsById(id);
	}

	@Override
	public List<FarmLeaseDTO> findAll() {
		List<FarmLease> farmLeases = farmLeaseRepository.findAll();
		List<FarmLeaseDTO> farmLeaseDTOs = farmLeases.stream()
				.map(farmLease -> farmLeaseMapper.toFarmLeaseDTO(farmLease)).collect(Collectors.toList());
		return farmLeaseDTOs;
	}

	@Override
	public List<FarmLeaseDTO> findAllById(Iterable<Long> ids) throws ResourceNotFoundException {
		List<FarmLease> farmLeases = farmLeaseRepository.findAllById(ids);
		List<FarmLeaseDTO> farmLeaseDTOs = farmLeases.stream()
				.map(farmLease -> farmLeaseMapper.toFarmLeaseDTO(farmLease)).collect(Collectors.toList());
		return farmLeaseDTOs;
	}

	@Override
	public FarmLeaseDTO findById(Long id) throws ResourceNotFoundException {
		FarmLease farmLease = farmLeaseRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Farm Lease with id " + id + " not found."));
		FarmLeaseDTO farmLeaseDTO = farmLeaseMapper.toFarmLeaseDTO(farmLease);
		return farmLeaseDTO;
	}

	@Override
	public List<FarmLeaseDTO> getFarmLeaseByFarmId(Long id) throws ResourceNotFoundException {
		List<FarmLease> farmLeases = farmLeaseRepository.findByFarmId(id)
				.orElseThrow(() -> new ResourceNotFoundException("Farm Lease with id " + id + " not found."));
		List<FarmLeaseDTO> farmLeaseDTOs = farmLeases.stream()
				.map(farmLease -> farmLeaseMapper.toFarmLeaseDTO(farmLease)).collect(Collectors.toList());
		return farmLeaseDTOs;
	}

	@Override
	public List<FarmLeaseDTO> getFarmLeaseByStatus(String status) throws ResourceNotFoundException {
		List<FarmLease> farmLeases = farmLeaseRepository.findByStatus(status)
				.orElseThrow(() -> new ResourceNotFoundException("Farm Lease with status " + status + " not found."));
		List<FarmLeaseDTO> farmLeaseDTOs = farmLeases.stream()
				.map(farmLease -> farmLeaseMapper.toFarmLeaseDTO(farmLease)).collect(Collectors.toList());
		return farmLeaseDTOs;
	}

	@Override
	public List<FarmLeaseDTO> getFarmLeaseByTenant(String tenant) throws ResourceNotFoundException {
		List<FarmLease> farmLeases = farmLeaseRepository.findByTenant(tenant)
				.orElseThrow(() -> new ResourceNotFoundException("Farm Lease with tenant " + tenant + " not found."));
		List<FarmLeaseDTO> farmLeaseDTOs = farmLeases.stream()
				.map(farmLease -> farmLeaseMapper.toFarmLeaseDTO(farmLease)).collect(Collectors.toList());
		return farmLeaseDTOs;
	}

	@Override
	public List<FarmLeaseDTO> getFarmLeaseByType(String type) throws ResourceNotFoundException {
		List<FarmLease> farmLeases = farmLeaseRepository.findByType(type)
				.orElseThrow(() -> new ResourceNotFoundException("Farm Lease with type " + type + " not found."));
		List<FarmLeaseDTO> farmLeaseDTOs = farmLeases.stream()
				.map(farmLease -> farmLeaseMapper.toFarmLeaseDTO(farmLease)).collect(Collectors.toList());
		return farmLeaseDTOs;
	}

	@Override
	public FarmLeaseDTO updateFarmLease(Long id, FarmLeaseDTO farmLeaseDTO) throws ResourceNotFoundException {
		if (id == null) {
			throw new IllegalArgumentException("Farm ID cannot be null.");
		}

		boolean exists = exists(id);
		if (!exists) {
			throw new ResourceNotFoundException("Farm Lease with id " + id + " not found.");
		}

		if (farmLeaseDTO.getPayments() == null) {
			farmLeaseDTO.setPayments(Collections.emptyList());
		}

		FarmLease updatedFarmLease = farmLeaseMapper.toFarmLease(farmLeaseDTO);
		updatedFarmLease.setId(id); // Preserve the original lease ID
		updatedFarmLease = farmLeaseRepository.save(updatedFarmLease);

		return farmLeaseMapper.toFarmLeaseDTO(updatedFarmLease);
	}

	@Override
	public FarmLeaseDTO createFarmLease(FarmLeaseDTO farmLeaseDTO) {
		validateCreation(farmLeaseDTO);

		FarmLease farmLease = farmLeaseMapper.toFarmLease(farmLeaseDTO);
		farmLease = farmLeaseRepository.save(farmLease);

		createPaymentsForLease(farmLeaseDTO, farmLease);

		return farmLeaseMapper.toFarmLeaseDTO(farmLease);
	}

	private void validateCreation(FarmLeaseDTO farmLeaseDTO) {
		if (farmLeaseDTO.getId() != null) {
			throw new IllegalArgumentException("Lease ID cannot be set on creation.");
		}
		if (farmLeaseDTO.getPayments() == null) {
			farmLeaseDTO.setPayments(Collections.emptyList());
		}
	}

	private void createPaymentsForLease(FarmLeaseDTO farmLeaseDTO, FarmLease farmLease) {
		farmLeaseDTO.getPayments().forEach(paymentDTO -> {
			FarmPayment payment = paymentMapper.toFarmPayment(paymentDTO);
			payment.setFarmLease(farmLease);
			paymentRepository.save(payment);
		});
	}

	@Override
	public void deleteAll() throws ResourceNotFoundException {
		Long count = farmLeaseRepository.count();
		if (count == 0) {
			throw new ResourceNotFoundException("There are no existing farm leases.");
		}
		farmLeaseRepository.deleteAll();
	}

	@Override
	public void delete(Long id) throws ResourceNotFoundException {

		if (id == null) {
			throw new IllegalArgumentException("Farm ID cannot be null.");
		}

		boolean exists = exists(id);
		if (!exists) {
			throw new ResourceNotFoundException("Farm Lease with id " + id + " not found.");
		}
		farmLeaseRepository.deleteById(id);
	}

	@Override
	public void deleteByIds(Iterable<Long> ids) throws ResourceNotFoundException {
		if (!ids.iterator().hasNext()) {
			return; // Avoid unnecessary processing if the ids are empty
		}
		farmLeaseRepository.deleteAllById(ids);
	}

}
