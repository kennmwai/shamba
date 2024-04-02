package com.kenm.spring.farmservice.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.kenm.spring.farmleaseservice.dto.FarmLeaseDTO;
import com.kenm.spring.farmservice.dto.FarmDTO;
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

	private static final Logger logger = LoggerFactory.getLogger(FarmService.class);

	@Autowired
	private FarmRepository farmRepository;

	@Autowired
	private FarmMapper farmMapper;

	@Autowired
	private LeaseServiceClient farmLeaseServiceClient;

	@Override
	public long count() {
		return farmRepository.count();
	}

	@Override
	public boolean exists(@NonNull Long id) {
		return farmRepository.existsById(id);
	}

	@Override
	public List<FarmDTO> findAll() {
		List<Farm> farms = farmRepository.findAll();
		List<FarmDTO> farmDetails = farmMapper.mapToFarmDTOs(farms);
		return farmDetails;
	}

	@Override
	public Page<FarmDTO> findAll(Pageable pageable) {
		List<Farm> farms = farmRepository.findAll(pageable).getContent();
		List<FarmDTO> farmDetails = farmMapper.mapToFarmDTOs(farms);
		return new PageImpl<>(farmDetails, pageable, farms.size());
	}

	@Override
	public List<FarmResourceDTO> getAllFarms() {
		List<Farm> farms = farmRepository.findAll();
		if (farms.isEmpty()) {
			return Collections.emptyList();
		}

		List<FarmDTO> farmDetails = farmMapper.mapToFarmDTOs(farms);
		List<FarmResourceDTO> farmResource = new ArrayList<>(farmDetails.size());

		farmDetails.parallelStream().forEach(farm -> {
			FarmResourceDTO farmResourceDTO = new FarmResourceDTO();
			farmResourceDTO.setFarm(farm);
			try {
				List<FarmLeaseDTO> leaseDetails = farmLeaseServiceClient.getLeaseByFarmId(farm.getId());
				farmResourceDTO.setLease(leaseDetails.isEmpty() ? Collections.emptyList() : leaseDetails);
			} catch (Exception e) {
				logger.error("Error: {}", e.getMessage());
				farmResourceDTO.setLease(Collections.emptyList());
			}
			synchronized (farmResource) {
				farmResource.add(farmResourceDTO);
			}
		});

		return farmResource;
	}

	@Override
	public FarmResourceDTO getFarmById(@NonNull Long farmId) throws ResourceNotFoundException {

		Farm farm = farmRepository.findById(farmId)
				.orElseThrow(() -> new ResourceNotFoundException("Farm with id " + farmId + " not found."));
		FarmDTO farmDetails = farmMapper.mapToFarmDTO(farm);

		FarmResourceDTO farmResource = new FarmResourceDTO();
		farmResource.setFarm(farmDetails);

		try {
			List<FarmLeaseDTO> leaseDetails = farmLeaseServiceClient.getLeaseByFarmId(farm.getId());
			if (leaseDetails.isEmpty()) {
				farmResource.setLease(Collections.emptyList());
			}
			farmResource.setLease(leaseDetails);
		} catch (Exception e) {
			logger.error("Error: {}", e.getMessage());
		}

		return farmResource;
	}

	@Override
	public List<FarmResourceDTO> getFarmsByIds(@NonNull Iterable<Long> ids) throws ResourceNotFoundException {
		List<Farm> farms = farmRepository.findAllById(ids);
		List<FarmDTO> farmDetails = farmMapper.mapToFarmDTOs(farms);
		
		List<FarmResourceDTO> farmResources = new ArrayList<>();

		farmDetails.parallelStream().forEach(farm -> {
			FarmResourceDTO farmResource = new FarmResourceDTO();
			farmResource.setFarm(farm);
			try {
				List<FarmLeaseDTO> leaseDetails = farmLeaseServiceClient.getLeaseByFarmId(farm.getId());
				farmResource.setLease(leaseDetails.isEmpty() ? Collections.emptyList() : leaseDetails);
			} catch (Exception e) {
				logger.error("Error: {}", e.getMessage());
				farmResource.setLease(Collections.emptyList());
			}
			farmResources.add(farmResource);
		});

		return farmResources;
	}

	@Override
	public FarmDTO updateFarm(@NonNull Long id, @NonNull FarmDTO farmDTO) throws ResourceNotFoundException {
		Farm updatedFarm = farmMapper.mapToFarm(farmDTO);
		updatedFarm.setId(id);
		updatedFarm = farmRepository.save(updatedFarm);
		FarmDTO updatedFarmDTO = farmMapper.mapToFarmDTO(updatedFarm);
		return updatedFarmDTO;
	}

	
	@Override
	public FarmDTO createFarm(@NonNull  FarmDTO farmDTO) {
		Farm newFarm = farmMapper.mapToFarm(farmDTO);
		newFarm = farmRepository.save(newFarm);
		FarmDTO createdFarmDTO = farmMapper.mapToFarmDTO(newFarm);
		return createdFarmDTO;
	}

	@Override
	public void deleteAll() {
		farmRepository.deleteAll();
	}

	@Override
	public void deleteAllById(@NonNull Iterable<Long> ids) {
		List<Farm> farms = farmRepository.findAllById(ids);
		List<Farm> farmsToDelete = farms.stream().filter(farm -> farm != null).collect(Collectors.toList());
		farmRepository.deleteAll(farmsToDelete);
	}

	@Override
	public void deleteById(@NonNull Long id) throws ResourceNotFoundException {
		Farm farm = farmRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Farm with id " + id + " not found."));
		farmRepository.delete(farm);
	}

	@Override
	public double calculateTotalPrice(@NonNull Long id) throws ResourceNotFoundException {
		Farm farm = farmRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Farm with id " + id + " not found."));
		double totalPrice = farm.getSize() * farm.getPricePerAcre();
		return totalPrice;
	}

}
