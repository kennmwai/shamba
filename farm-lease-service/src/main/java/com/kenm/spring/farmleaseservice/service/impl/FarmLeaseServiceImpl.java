/**
 *
 */
package com.kenm.spring.farmleaseservice.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kenm.spring.farmleaseservice.dto.FarmLeaseDTO;
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
	public FarmLeaseDTO createFarmLease(FarmLeaseDTO farmLeaseDTO) {
		FarmLease farmLease = farmLeaseMapper.toFarmLease(farmLeaseDTO);
		farmLease = farmLeaseRepository.save(farmLease);
		FarmLeaseDTO createdFarmLeaseDTO = farmLeaseMapper.toFarmLeaseDTO(farmLease);
		return createdFarmLeaseDTO;
	}

	@Override
	public void deleteAll() {
		farmLeaseRepository.deleteAll();
	}

	@Override
	public void deleteAllById(Iterable<Long> ids) throws ResourceNotFoundException {
		List<FarmLease> farmLeases = farmLeaseRepository.findAllById(ids);
		List<FarmLease> farmLeasesToDelete = farmLeases.stream().filter(farmLease -> farmLease != null)
				.collect(Collectors.toList());
		farmLeaseRepository.deleteAll(farmLeasesToDelete);
	}

	@Override
	public void deleteById(Long id) throws ResourceNotFoundException {
		FarmLease farmLease = farmLeaseRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Farm Lease with id " + id + " not found."));
		farmLeaseRepository.delete(farmLease);
	}

	@Override
	public boolean existsById(Long id) {
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
		FarmLease updatedFarmLease = farmLeaseMapper.toFarmLease(farmLeaseDTO);
		updatedFarmLease.setId(id);
		updatedFarmLease = farmLeaseRepository.save(updatedFarmLease);
		FarmLeaseDTO updatedFarmLeaseDTO = farmLeaseMapper.toFarmLeaseDTO(updatedFarmLease);
		return updatedFarmLeaseDTO;
	}

}
