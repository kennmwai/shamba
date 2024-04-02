/**
 *
 */
package com.kenm.spring.farmservice.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;

import com.kenm.spring.farmservice.dto.FarmDTO;
import com.kenm.spring.farmservice.dto.FarmResourceDTO;
import com.kenm.spring.farmservice.exception.ResourceNotFoundException;

import jakarta.validation.Valid;

/**
 * @author User
 *
 */
public interface FarmService {

	long count();

	boolean exists(@NonNull Long id);

	List<FarmDTO> findAll();

	Page<FarmDTO> findAll(Pageable pageable);

	FarmResourceDTO getFarmById(@NonNull Long farmId) throws ResourceNotFoundException;

	List<FarmResourceDTO> getFarmsByIds(@NonNull Iterable<Long> ids) throws ResourceNotFoundException;

	List<FarmResourceDTO> getAllFarms();

	FarmDTO updateFarm(@NonNull Long id, @Valid @NonNull FarmDTO farmDTO) throws ResourceNotFoundException;

	FarmDTO createFarm(FarmDTO farmDTO);

	void deleteAll();

	void deleteAllById(@NonNull Iterable<Long> ids);

	void deleteById(@NonNull Long id) throws ResourceNotFoundException;

	double calculateTotalPrice(@NonNull Long id) throws ResourceNotFoundException;

}