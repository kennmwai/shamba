/**
 *
 */
package com.kenm.spring.farmservice.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kenm.spring.farmservice.dto.FarmDetailsDTO;
import com.kenm.spring.farmservice.dto.FarmResourceDTO;
import com.kenm.spring.farmservice.exception.ResourceNotFoundException;

/**
 * @author User
 *
 */
public interface FarmService {
	List<FarmDetailsDTO> findAll();

	List<FarmResourceDTO> getAllFarms();

	Page<FarmDetailsDTO> findAll(Pageable pageable);

	FarmDetailsDTO findById(Long id) throws ResourceNotFoundException;

	FarmDetailsDTO createFarm(FarmDetailsDTO farmDTO);

	FarmDetailsDTO updateFarm(Long id, FarmDetailsDTO farmDTO) throws ResourceNotFoundException;

	void deleteById(Long id) throws ResourceNotFoundException;

	void deleteAll();

	boolean existsById(Long id);

	long count();

	List<FarmDetailsDTO> findAllById(Iterable<Long> ids);

	void deleteAllById(Iterable<Long> ids);

	double calculateTotalPrice(Long id) throws ResourceNotFoundException;

}