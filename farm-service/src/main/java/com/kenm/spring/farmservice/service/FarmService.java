/**
 *
 */
package com.kenm.spring.farmservice.service;

import java.util.List;

import com.kenm.spring.farmservice.dto.FarmDTO;
import com.kenm.spring.farmservice.exception.RecordNotFoundException;

/**
 * @author User
 *
 */
public interface FarmService {
	List<FarmDTO> findAll();

	FarmDTO findById(Long id) throws RecordNotFoundException;

	FarmDTO createFarm(FarmDTO farmDTO);

	FarmDTO updateFarm(Long id, FarmDTO farmDTO) throws RecordNotFoundException;

	void deleteById(Long id) throws RecordNotFoundException;

	void deleteAll();

	boolean existsById(Long id);

	long count();

	List<FarmDTO> findAllById(Iterable<Long> ids);

	void deleteAllById(Iterable<Long> ids);

	double calculateTotalPrice(Long id) throws RecordNotFoundException;

}