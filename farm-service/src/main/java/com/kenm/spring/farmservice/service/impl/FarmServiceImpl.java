/**
 *
 */
package com.kenm.spring.farmservice.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kenm.spring.farmleaseservice.dto.FarmLeaseDTO;
import com.kenm.spring.farmservice.dto.FarmDetailsDTO;
import com.kenm.spring.farmservice.dto.FarmResourceDTO;
import com.kenm.spring.farmservice.entity.Farm;
import com.kenm.spring.farmservice.exception.ResourceNotFoundException;
import com.kenm.spring.farmservice.mapper.FarmMapper;
import com.kenm.spring.farmservice.repository.FarmRepository;
import com.kenm.spring.farmservice.service.FarmService;
import com.kenm.spring.farmservice.service.LeaseServiceClient;

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
	private LeaseServiceClient farmLeaseServiceClient;

	@Override
	public List<FarmDetailsDTO> findAll() {
		List<Farm> farms = farmRepository.findAll();
		List<FarmDetailsDTO> farmDetails = farms.stream()
				.map(farm -> farmMapper.mapToFarmDetailsDTO(farm)).collect(Collectors.toList());
		return farmDetails;
	}

	@Override
	public List<FarmResourceDTO> getAllFarms() {
		List<Farm> farms = farmRepository.findAll();
		List<FarmResourceDTO> farmResources = farms.stream()
				.map(farm -> {
					FarmDetailsDTO farmDetails = farmMapper.mapToFarmDetailsDTO(farm);
					FarmLeaseDTO leaseDetails = null; // Initialize leaseDetails to null

					try {
						leaseDetails = farmLeaseServiceClient.getLeaseByFarmId(farm.getId());
					} catch (Exception e) {
						// Log or handle the exception as needed
						System.err.println("Error fetching lease details for farm with ID: " + farm.getId());
					}
	
					FarmResourceDTO farmResource = new FarmResourceDTO();
					farmResource.setFarm(farmDetails);
					farmResource.setLease(leaseDetails);

					return farmResource;
				})
				.filter(Objects::nonNull) // Filter out null FarmResource objects
				.collect(Collectors.toList());

		return farmResources;
	}

	@Override
	public Page<FarmDetailsDTO> findAll(Pageable pageable) {
		List<Farm> farms = farmRepository.findAll(pageable).getContent();
		List<FarmDetailsDTO> farmDetails = farms.stream()
				.map(farm -> farmMapper.mapToFarmDetailsDTO(farm)).collect(Collectors.toList());
		return new PageImpl<>(farmDetails, pageable, farms.size());
	}

	@Override
	public FarmDetailsDTO findById(Long id) throws ResourceNotFoundException {
		Farm farm = farmRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Farm with id " + id + " not found."));

		// Long leaseId = farm.getLeaseId();
		// if (leaseId == null || leaseId == 0) {
		// 	return farmMapper.mapToFarmDetails(farm);
		// }

		// FarmLeaseDTO farmLeaseDTO = farmLeaseServiceClient.getLeaseById(leaseId);
		FarmDetailsDTO farmDTO = farmMapper.mapToFarmDetailsDTO(farm);
		// farmDTO.setLeaseId(farmLeaseDTO.getId());

		return farmDTO;
	}

	@Override
	public List<FarmDetailsDTO> findAllById(Iterable<Long> ids) {
		List<Farm> farms = farmRepository.findAllById(ids);
		List<FarmDetailsDTO> farmDetails = farms.stream()
				.map(farm -> farmMapper.mapToFarmDetailsDTO(farm)).collect(Collectors.toList());
		return farmDetails;
	}

	@Override
	public FarmDetailsDTO createFarm(FarmDetailsDTO farmDTO) {
		Farm newFarm = farmMapper.mapToFarm(farmDTO);
		newFarm = farmRepository.save(newFarm);
		FarmDetailsDTO createdFarmDTO = farmMapper.mapToFarmDetailsDTO(newFarm);
		return createdFarmDTO;
	}

	@Override
	public FarmDetailsDTO updateFarm(Long id, FarmDetailsDTO farmDTO) throws ResourceNotFoundException {
		Farm updatedFarm = farmMapper.mapToFarm(farmDTO);
		updatedFarm.setId(id);
		updatedFarm = farmRepository.save(updatedFarm);
		FarmDetailsDTO updatedFarmDTO = farmMapper.mapToFarmDetailsDTO(updatedFarm);
		return updatedFarmDTO;
	}

	@Override
	public void deleteById(Long id) throws ResourceNotFoundException {
		Farm farm = farmRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Farm with id " + id + " not found."));
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
	public double calculateTotalPrice(Long id) throws ResourceNotFoundException {
		Farm farm = farmRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Farm with id " + id + " not found."));
		double totalPrice = farm.getSize() * farm.getPricePerAcre();
		return totalPrice;
	}

	// Leases Methods
	// //findAllLeases
	// public FarmDTO findLeaseById(Long id) throws RecordNotFoundException {
	// 	FarmLeaseDTO farmLeaseDTO = farmLeaseServiceClient.getLeaseById(id);
	// 	FarmDTO farmDTO = farmMapper.mapToFarmDetails(farmLeaseDTO.getFarmId());
	// 	farmDTO.setLeaseId(farmLeaseDTO.getId());
	// 	return farmDTO;
	// }
}
