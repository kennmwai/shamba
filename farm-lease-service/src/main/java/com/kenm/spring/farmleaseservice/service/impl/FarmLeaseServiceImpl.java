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
import com.kenm.spring.farmleaseservice.exception.RecordNotFoundException;
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
	public List<FarmLeaseDTO> findAll() {
		List<FarmLease> farmLeases = farmLeaseRepository.findAll();
		List<FarmLeaseDTO> farmLeaseDTOs = farmLeases.stream()
				.map(farmLease -> farmLeaseMapper.toFarmLeaseDTO(farmLease)).collect(Collectors.toList());
		return farmLeaseDTOs;
	}

	@Override
	public FarmLeaseDTO findById(Long id) throws RecordNotFoundException {
		FarmLease farmLease = farmLeaseRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Farm Lease with id " + id + " not found."));
		FarmLeaseDTO farmLeaseDTO = farmLeaseMapper.toFarmLeaseDTO(farmLease);
		return farmLeaseDTO;
	}

	@Override
	public FarmLeaseDTO createFarmLease(FarmLeaseDTO farmLeaseDTO) {
		FarmLease farmLease = farmLeaseMapper.toFarmLease(farmLeaseDTO);
		farmLease = farmLeaseRepository.save(farmLease);
		FarmLeaseDTO createdFarmLeaseDTO = farmLeaseMapper.toFarmLeaseDTO(farmLease);
		return createdFarmLeaseDTO;
	}

	@Override
	public FarmLeaseDTO updateFarmLease(Long id, FarmLeaseDTO farmLeaseDTO) throws RecordNotFoundException {
	    FarmLease updatedFarmLease = farmLeaseMapper.toFarmLease(farmLeaseDTO);
	    updatedFarmLease.setId(id);
	    updatedFarmLease = farmLeaseRepository.save(updatedFarmLease);
	    FarmLeaseDTO updatedFarmLeaseDTO = farmLeaseMapper.toFarmLeaseDTO(updatedFarmLease);
	    return updatedFarmLeaseDTO;
	}

	@Override
	public void deleteById(Long id) throws RecordNotFoundException {
		FarmLease farmLease = farmLeaseRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Farm Lease with id " + id + " not found."));
		farmLeaseRepository.delete(farmLease);
	}

	@Override
	public void deleteAll() {
		farmLeaseRepository.deleteAll();
	}

	@Override
	public boolean existsById(Long id) {
		return farmLeaseRepository.existsById(id);
	}

	@Override
	public long count() {
		return farmLeaseRepository.count();
	}

	@Override
	public List<FarmLeaseDTO> findAllById(Iterable<Long> ids) {
		List<FarmLease> farmLeases = farmLeaseRepository.findAllById(ids);
		List<FarmLeaseDTO> farmLeaseDTOs = farmLeases.stream()
				.map(farmLease -> farmLeaseMapper.toFarmLeaseDTO(farmLease)).collect(Collectors.toList());
		return farmLeaseDTOs;
	}

	@Override
	public void deleteAllById(Iterable<Long> ids) {
		List<FarmLease> farmLeases = farmLeaseRepository.findAllById(ids);
		List<FarmLease> farmLeasesToDelete = farmLeases.stream().filter(farmLease -> farmLease != null)
				.collect(Collectors.toList());
		farmLeaseRepository.deleteAll(farmLeasesToDelete);
	}

	@Override
	public double calculateTotalPrice(Long id) throws RecordNotFoundException {
		FarmLease farmLease = farmLeaseRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Farm Lease with id " + id + " not found."));
		double totalPrice = farmLease.getAcres() * farmLease.getPricePerAcre();
		return totalPrice;
	}

}
