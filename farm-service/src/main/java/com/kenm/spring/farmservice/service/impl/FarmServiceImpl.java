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
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
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

	@Autowired
	private FarmRepository farmRepository;

	@Autowired
	private FarmMapper farmMapper;

	@Autowired
	private LeaseServiceClient farmLeaseServiceClient;

	@Override
	public List<FarmDTO> findAll() {
		List<Farm> farms = farmRepository.findAll();
		List<FarmDTO> farmDetails = farms.stream().map(farm -> farmMapper.mapToFarmDetailsDTO(farm))
				.collect(Collectors.toList());
		return farmDetails;
	}

	@Override
	public List<FarmResourceDTO> getAllFarms() {
		List<Farm> farms = farmRepository.findAll();
		List<FarmResourceDTO> farmResources = farms.stream().map(farm -> {
			FarmDTO farmDetails = farmMapper.mapToFarmDetailsDTO(farm);
			FarmLeaseDTO leaseDetails = null; // Initialize leaseDetails to null

			try {
				leaseDetails = farmLeaseServiceClient.getLeaseByFarmId(farm.getId());
			} catch (Exception e) {
				// TODO Log or handle the exception
				System.err.println("Error fetching lease details for farm with ID: " + farm.getId());
			}

			FarmResourceDTO farmResource = new FarmResourceDTO();
			farmResource.setFarm(farmDetails);
			farmResource.setLease(leaseDetails);

			return farmResource;
		}).filter(Objects::nonNull) // Filter out null FarmResource objects
				.collect(Collectors.toList());

		return farmResources;
	}

	@Override
	public Page<FarmDTO> findAll(Pageable pageable) {
		List<Farm> farms = farmRepository.findAll(pageable).getContent();
		List<FarmDTO> farmDetails = farms.stream().map(farm -> farmMapper.mapToFarmDetailsDTO(farm))
				.collect(Collectors.toList());
		return new PageImpl<>(farmDetails, pageable, farms.size());
	}

	@Override
	public FarmResourceDTO findById(Long farmId) throws ResourceNotFoundException {

		Farm farm = farmRepository.findById(farmId)
				.orElseThrow(() -> new ResourceNotFoundException("Farm with id " + farmId + " not found."));

		FarmDTO farmDetails = farmMapper.mapToFarmDetailsDTO(farm);
		FarmLeaseDTO leaseDetails = null; // Initialize leaseDetails to null

		try {
			leaseDetails = farmLeaseServiceClient.getLeaseByFarmId(farm.getId());
		} catch (Exception e) {
			// TODO Log or handle the exception
			System.err.println("Error fetching lease details for farm with ID: " + farm.getId());
		}

		FarmResourceDTO farmResource = new FarmResourceDTO();
		farmResource.setFarm(farmDetails);
		farmResource.setLease(leaseDetails);

		return farmResource;
	}

	@Override
	public List<FarmDTO> findAllById(Iterable<Long> ids) {
		List<Farm> farms = farmRepository.findAllById(ids);
		List<FarmDTO> farmDetails = farms.stream().map(farm -> farmMapper.mapToFarmDetailsDTO(farm))
				.collect(Collectors.toList());
		return farmDetails;
	}

	@Override
	public FarmDTO createFarm(FarmDTO farmDTO) {
		Farm newFarm = farmMapper.mapToFarm(farmDTO);
		newFarm = farmRepository.save(newFarm);
		FarmDTO createdFarmDTO = farmMapper.mapToFarmDetailsDTO(newFarm);
		return createdFarmDTO;
	}

	@Override
	public FarmDTO updateFarm(Long id, FarmDTO farmDTO) throws ResourceNotFoundException {
		Farm updatedFarm = farmMapper.mapToFarm(farmDTO);
		updatedFarm.setId(id);
		updatedFarm = farmRepository.save(updatedFarm);
		FarmDTO updatedFarmDTO = farmMapper.mapToFarmDetailsDTO(updatedFarm);
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
		List<Farm> farmsToDelete = farms.stream().filter(farm -> farm != null).collect(Collectors.toList());
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
}
