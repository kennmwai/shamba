/**
 *
 */
package com.kenm.spring.farmleaseservice.service;

import java.util.List;

import com.kenm.spring.farmleaseservice.dto.FarmLeaseDTO;
import com.kenm.spring.farmleaseservice.exception.RecordNotFoundException;

/**
 * @author User
 *
 */
public interface FarmLeaseService {
	List<FarmLeaseDTO> findAll();

	FarmLeaseDTO findById(Long id) throws RecordNotFoundException;

	FarmLeaseDTO createFarmLease(FarmLeaseDTO farmLeaseDTO);

	FarmLeaseDTO updateFarmLease(Long id, FarmLeaseDTO farmLeaseDTO) throws RecordNotFoundException;

	void deleteById(Long id) throws RecordNotFoundException;

	void deleteAll();

	boolean existsById(Long id);

	long count();

	List<FarmLeaseDTO> findAllById(Iterable<Long> ids);

	void deleteAllById(Iterable<Long> ids);

	double calculateTotalPrice(Long id) throws RecordNotFoundException;

}