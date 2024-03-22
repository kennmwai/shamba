/**
 *
 */
package com.kenm.spring.farmleaseservice.service;

import java.util.List;

import com.kenm.spring.farmleaseservice.dto.FarmLeaseDTO;
import com.kenm.spring.farmleaseservice.exception.ResourceNotFoundException;

/**
 * @author User
 *
 */
public interface FarmLeaseService {
	List<FarmLeaseDTO> findAll();

	FarmLeaseDTO findById(Long id) throws ResourceNotFoundException;

	FarmLeaseDTO findByFarmId(Long id) throws ResourceNotFoundException;

	FarmLeaseDTO createFarmLease(FarmLeaseDTO farmLeaseDTO);

	FarmLeaseDTO updateFarmLease(Long id, FarmLeaseDTO farmLeaseDTO) throws ResourceNotFoundException;

	void deleteById(Long id) throws ResourceNotFoundException;

	void deleteAll();

	boolean existsById(Long id);

	long count();

	List<FarmLeaseDTO> findAllById(Iterable<Long> ids) throws ResourceNotFoundException;

	void deleteAllById(Iterable<Long> ids) throws ResourceNotFoundException;

	// double calculateTotalPrice(Long id) throws RecordNotFoundException;

}