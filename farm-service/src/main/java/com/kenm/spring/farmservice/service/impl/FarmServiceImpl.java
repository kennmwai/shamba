/**
 *
 */
package com.kenm.spring.farmservice.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kenm.spring.farmservice.dto.FarmDTO;
import com.kenm.spring.farmservice.entity.Farm;
import com.kenm.spring.farmservice.exception.RecordNotFoundException;
import com.kenm.spring.farmservice.mapper.FarmMapper;
import com.kenm.spring.farmservice.repository.FarmRepository;
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

	@Override
	public List<FarmDTO> findAll() {
		List<Farm> farms = farmRepository.findAll();
		List<FarmDTO> farmDTOs = farms.stream()
				.map(farm -> farmMapper.toFarmDTO(farm)).collect(Collectors.toList());
		return farmDTOs;
	}

	@Override
	public FarmDTO findById(Long id) throws RecordNotFoundException {
		Farm farm = farmRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Farm with id " + id + " not found."));
		FarmDTO farmDTO = farmMapper.toFarmDTO(farm);
		return farmDTO;
	}

	@Override
	public FarmDTO createFarm(FarmDTO farmDTO) {
		Farm farm = farmMapper.toFarm(farmDTO);
		farm = farmRepository.save(farm);
		FarmDTO createdFarmDTO = farmMapper.toFarmDTO(farm);
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
	public boolean existsById(Long id) {
		return farmRepository.existsById(id);
	}

	@Override
	public long count() {
		return farmRepository.count();
	}

	@Override
	public List<FarmDTO> findAllById(Iterable<Long> ids) {
		List<Farm> farms = farmRepository.findAllById(ids);
		List<FarmDTO> farmDTOs = farms.stream()
				.map(farm -> farmMapper.toFarmDTO(farm)).collect(Collectors.toList());
		return farmDTOs;
	}

	@Override
	public void deleteAllById(Iterable<Long> ids) {
		List<Farm> farms = farmRepository.findAllById(ids);
		List<Farm> farmsToDelete = farms.stream().filter(farm -> farm != null)
				.collect(Collectors.toList());
		farmRepository.deleteAll(farmsToDelete);
	}

	@Override
	public double calculateTotalPrice(Long id) throws RecordNotFoundException {
		Farm farm = farmRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Farm with id " + id + " not found."));
		double totalPrice = farm.getAcres() * farm.getPricePerAcre();
		return totalPrice;
	}

}