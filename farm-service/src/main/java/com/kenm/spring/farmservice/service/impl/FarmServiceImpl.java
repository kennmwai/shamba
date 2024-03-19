/**
 *
 */
package com.kenm.spring.farmservice.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kenm.spring.farmleaseservice.dto.FarmLeaseDTO;
import com.kenm.spring.farmservice.dto.FarmDTO;
import com.kenm.spring.farmservice.entity.Farm;
import com.kenm.spring.farmservice.exception.RecordNotFoundException;
import com.kenm.spring.farmservice.mapper.FarmMapper;
import com.kenm.spring.farmservice.repository.FarmRepository;
import com.kenm.spring.farmservice.service.FarmLeaseServiceClient;
import com.kenm.spring.farmservice.service.FarmService;

/**
 * @author User
 *
 */
@Service
public class FarmServiceImpl implements FarmService {

	@Autowired
	private FarmRepository farmRepository;

	@Autowired
	private FarmMapper farmMapper;

	@Autowired
	private FarmLeaseServiceClient farmLeaseServiceClient;

	@Override
	public List<FarmDTO> findAll() {
		List<Farm> farms = farmRepository.findAll();
		List<FarmDTO> farmDTOs = farms.stream()
				.map(farm -> farmMapper.toFarmDTO(farm)).collect(Collectors.toList());
		return farmDTOs;
	}

	@Override
	public Page<FarmDTO> findAll(Pageable pageable) {
		List<Farm> farms = farmRepository.findAll(pageable).getContent();
		List<FarmDTO> farmDTOs = farms.stream()
				.map(farm -> farmMapper.toFarmDTO(farm)).collect(Collectors.toList());
		return new PageImpl<>(farmDTOs, pageable, farms.size());
	}

	@Override
	public FarmDTO findById(Long id) throws RecordNotFoundException {
		Farm farm = farmRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Farm with id " + id + " not found."));

		Long leaseId = farm.getLeaseId();
		if (leaseId == null || leaseId == 0) {
			return farmMapper.toFarmDTO(farm);
		}

		FarmLeaseDTO farmLeaseDTO = farmLeaseServiceClient.getLeaseById(leaseId);
		FarmDTO farmDTO = farmMapper.toFarmDTO(farm);
		farmDTO.setLeaseId(farmLeaseDTO.getId());

		return farmDTO;
	}

	@Override
	public List<FarmDTO> findAllById(Iterable<Long> ids) {
		List<Farm> farms = farmRepository.findAllById(ids);
		List<FarmDTO> farmDTOs = farms.stream()
				.map(farm -> farmMapper.toFarmDTO(farm)).collect(Collectors.toList());
		return farmDTOs;
	}

	@Override
	public FarmDTO createFarm(FarmDTO farmDTO) {
		Farm newFarm = farmMapper.toFarm(farmDTO);
		newFarm = farmRepository.save(newFarm);
		FarmDTO createdFarmDTO = farmMapper.toFarmDTO(newFarm);
		return createdFarmDTO;
	}

	@Override
	public FarmDTO updateFarm(Long id, FarmDTO farmDTO) throws RecordNotFoundException {
		Farm updatedFarm = farmMapper.toFarm(farmDTO);
		updatedFarm.setId(id);
		updatedFarm = farmRepository.save(updatedFarm);
		FarmDTO updatedFarmDTO = farmMapper.toFarmDTO(updatedFarm);
		return updatedFarmDTO;
	}

	@Override
	public void deleteById(Long id) throws RecordNotFoundException {
		Farm farm = farmRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Farm with id " + id + " not found."));
		farmRepository.delete(farm);
	}

	@Override
	public void deleteAll() {
		farmRepository.deleteAll();
	}

	@Override
	public void deleteAllById(Iterable<Long> ids) {
		List<Farm> farms = farmRepository.findAllById(ids);
		List<Farm> farmsToDelete = farms.stream().filter(farm -> farm != null)
				.collect(Collectors.toList());
		farmRepository.deleteAll(farmsToDelete);
	}

	@Override
	public boolean existsById(Long id) {
		return farmRepository.existsById(id);
	}

	@Override
	public long count() {
		return farmRepository.count();
	}

	@Override
	public double calculateTotalPrice(Long id) throws RecordNotFoundException {
		Farm farm = farmRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Farm with id " + id + " not found."));
		double totalPrice = farm.getFarmSize() * farm.getPricePerAcre();
		return totalPrice;
	}

}
