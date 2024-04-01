/**
 *
 */
package com.kenm.spring.farmservice.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kenm.spring.farmservice.dto.FarmDTO;
import com.kenm.spring.farmservice.dto.FarmResourceDTO;
import com.kenm.spring.farmservice.exception.ResourceNotFoundException;

/**
 * @author User
 *
 */
public interface FarmService {

	List<FarmDTO> findAll();

	List<FarmResourceDTO> getAllFarms();

	Page<FarmDTO> findAll(Pageable pageable);

	FarmResourceDTO  findById(Long farmId) throws ResourceNotFoundException;

	FarmDTO createFarm(FarmDTO farmDTO);

	FarmDTO updateFarm(Long id, FarmDTO farmDTO) throws ResourceNotFoundException;

	void deleteById(Long id) throws ResourceNotFoundException;

	void deleteAll();

	boolean existsById(Long id);

	long count();

	List<FarmDTO> findAllById(Iterable<Long> ids);

	void deleteAllById(Iterable<Long> ids);

	double calculateTotalPrice(Long id) throws ResourceNotFoundException;

}