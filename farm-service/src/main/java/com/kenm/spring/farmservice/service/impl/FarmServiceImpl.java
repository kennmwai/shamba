package com.kenm.spring.farmservice.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.kenm.spring.farmservice.dto.FarmDTO;
import com.kenm.spring.farmservice.dto.payload.FarmResourceDTO;
import com.kenm.spring.farmservice.dto.payload.request.FarmReqDTO;
import com.kenm.spring.farmservice.dto.payload.response.FarmLeaseDTO;
import com.kenm.spring.farmservice.entity.Farm;
import com.kenm.spring.farmservice.exception.ResourceAlreadyExistsException;
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

		CompletableFuture<List<FarmLeaseDTO>> leaseDetailsFuture = CompletableFuture.supplyAsync(() -> {
			try {
				return farmLeaseServiceClient.getLeaseByFarmId(farm.getId());
			} catch (Exception e) {
				logger.error("Error: {}", e.getMessage());
				return Collections.emptyList();
			}
		});

		// Join the future to wait for the result without blocking the thread
		List<FarmLeaseDTO> leaseDetails = leaseDetailsFuture.join();
		farmResource.setLease(leaseDetails.isEmpty() ? Collections.emptyList() : leaseDetails);

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

			CompletableFuture<List<FarmLeaseDTO>> leaseDetailsFuture = CompletableFuture.supplyAsync(() -> {
				try {
					return farmLeaseServiceClient.getLeaseByFarmId(farm.getId());
				} catch (Exception e) {
					logger.error("Error: {}", e.getMessage());
					return Collections.emptyList();
				}
			});

			// Join the future to wait for the result without blocking the thread
			List<FarmLeaseDTO> leaseDetails = leaseDetailsFuture.join();
			farmResource.setLease(leaseDetails.isEmpty() ? Collections.emptyList() : leaseDetails);

			farmResources.add(farmResource);
		});

		return farmResources;
	}

	@Override
	public FarmDTO updateFarm(@NonNull Long FarmId, @NonNull FarmDTO farmDTO) throws ResourceNotFoundException {

		if (!exists(FarmId)) {
			 	throw new ResourceNotFoundException("Farm with id {" + FarmId + "} does not exists.");
		 }
		 
		Farm updatedFarm = farmMapper.mapToFarm(farmDTO);
		updatedFarm.setId(FarmId);
		updatedFarm = farmRepository.save(updatedFarm);
		FarmDTO updatedFarmDTO = farmMapper.mapToFarmDTO(updatedFarm);
		return updatedFarmDTO;
	}

	@Override
	public FarmDTO createFarm(@NonNull FarmReqDTO farmDTO) throws ResourceAlreadyExistsException {
		Farm newFarm = farmMapper.mapToFarm(farmDTO);

		FarmDTO createdFarmDTO = farmMapper.mapToFarmDTO(farmRepository.save(newFarm));
		return createdFarmDTO;
	}

	@Override
	public void deleteAll() {
		farmRepository.deleteAll();
	}

	@Override
	public void deleteAllById(@NonNull Iterable<Long> ids) {
		farmRepository.deleteAllById(ids);
	}

	@Override
	public void deleteById(@NonNull Long id) throws ResourceNotFoundException {
		boolean exists = this.exists(id);
		if (!exists) {
			throw new ResourceNotFoundException("Farm with id " + id + " not found.");
		}
		farmRepository.deleteById(id);
	}

	@Override
	public double calculateTotalPrice(@NonNull Long id) throws ResourceNotFoundException {
		Farm farm = farmRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Farm with id " + id + " not found."));
		double totalPrice = farm.getSize() * farm.getPricePerAcre();
		return totalPrice;
	}

}
