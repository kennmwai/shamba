/**
 *
 */
package com.kenm.spring.farmleaseservice.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.kenm.spring.farmleaseservice.dto.FarmLeaseDTO;
import com.kenm.spring.farmleaseservice.dto.payload.CreateLeaseRequest;
import com.kenm.spring.farmleaseservice.dto.payload.UpdateLeaseRequest;
import com.kenm.spring.farmleaseservice.entity.FarmLease;
import com.kenm.spring.farmleaseservice.exception.ResourceNotFoundException;
import com.kenm.spring.farmleaseservice.mapper.FarmLeaseMapper;
import com.kenm.spring.farmleaseservice.repository.FarmLeaseRepository;
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
	private FarmLeaseMapper farmLeaseMapper;

	@Override
	public long count() {
		return farmLeaseRepository.count();
	}

	@Override
	public boolean exists(@NonNull Long id) {
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
	public List<FarmLeaseDTO> findAllById(@NonNull Iterable<Long> ids) throws ResourceNotFoundException {
		List<FarmLease> farmLeases = farmLeaseRepository.findAllById(ids);
		List<FarmLeaseDTO> farmLeaseDTOs = farmLeases.stream()
				.map(farmLease -> farmLeaseMapper.toFarmLeaseDTO(farmLease)).collect(Collectors.toList());
		return farmLeaseDTOs;
	}

	@Override
	public FarmLeaseDTO findById(@NonNull Long id) throws ResourceNotFoundException {
		FarmLease farmLease = farmLeaseRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Farm Lease with id " + id + " not found."));
		FarmLeaseDTO farmLeaseDTO = farmLeaseMapper.toFarmLeaseDTO(farmLease);
		return farmLeaseDTO;
	}

	@Override
	public List<FarmLeaseDTO> getFarmLeaseByFarmId(@NonNull Long id) throws ResourceNotFoundException {
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
	public FarmLeaseDTO updateFarmLease(@NonNull Long id, UpdateLeaseRequest updateLeaseDTO) throws ResourceNotFoundException {
		if (!exists(id)) {
			throw new ResourceNotFoundException("Farm Lease with ID " + id + " not found.");
		}

		FarmLease updatedFarmLease = farmLeaseMapper.toFarmLease(updateLeaseDTO);
		updatedFarmLease.setLeaseId(id);
		farmLeaseRepository.save(updatedFarmLease);

		// paymentService.updatePayments(farmLeaseDTO.getPayments(), updatedFarmLease);

		return farmLeaseMapper.toFarmLeaseDTO(updatedFarmLease);
	}

	@Override
	public FarmLeaseDTO createFarmLease(CreateLeaseRequest createLeaseDTO) {
		// validateCreation(createLease);

		FarmLease farmLease = farmLeaseMapper.toFarmLease(createLeaseDTO);
		farmLease = farmLeaseRepository.save(farmLease);

		// createPaymentsForLease(farmLeaseDTO, farmLease);

		return farmLeaseMapper.toFarmLeaseDTO(farmLease);
	}

//	private void validateCreation(CreateLeaseRequest createLease) {
//		if (createLease.getId() != null) {
//			throw new IllegalArgumentException("Lease ID cannot be set on creation.");
//		}
//		if (createLease.getPayments() == null) {
//			createLease.setPayments(Collections.emptyList());
//		}
//	}

//	private void createPaymentsForLease(FarmLeaseDTO farmLeaseDTO, FarmLease farmLease) {
//		farmLeaseDTO.getPayments().forEach(payment -> paymentService.createPayment(payment, farmLease));
//	}
//
//	private void updatePaymentsForLease(FarmLeaseDTO farmLeaseDTO, FarmLease farmLease) {
//		farmLeaseDTO.getPayments().forEach(payment -> paymentService.updatePayment(payment, farmLease));
//	}

	@Override
	public void deleteAll() throws ResourceNotFoundException {
		Long count = farmLeaseRepository.count();
		if (count == 0) {
			throw new ResourceNotFoundException("There are no existing farm leases.");
		}
		farmLeaseRepository.deleteAll();
	}

	@Override
	public void delete(@NonNull Long id) throws ResourceNotFoundException {

		boolean exists = exists(id);
		if (!exists) {
			throw new ResourceNotFoundException("Farm Lease with id " + id + " not found.");
		}
		farmLeaseRepository.deleteById(id);
	}

	@Override
	public void deleteByIds(@NonNull Iterable<Long> ids) throws ResourceNotFoundException {
		if (!ids.iterator().hasNext()) {
			return; // Avoid unnecessary processing if the ids are empty
		}
		farmLeaseRepository.deleteAllById(ids);
	}

}
