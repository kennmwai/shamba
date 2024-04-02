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
	long count();

	FarmLeaseDTO createFarmLease(FarmLeaseDTO farmLeaseDTO);

//	FarmLeaseDTO findByFarmId(Long id) throws ResourceNotFoundException;

	void deleteAll();

	void deleteAllById(Iterable<Long> ids) throws ResourceNotFoundException;

	void deleteById(Long id) throws ResourceNotFoundException;

	boolean existsById(Long id);

	List<FarmLeaseDTO> findAll();

	List<FarmLeaseDTO> findAllById(Iterable<Long> ids) throws ResourceNotFoundException;

	FarmLeaseDTO findById(Long id) throws ResourceNotFoundException;

	List<FarmLeaseDTO> getFarmLeaseByFarmId(Long id) throws ResourceNotFoundException;

	List<FarmLeaseDTO> getFarmLeaseByStatus(String status) throws ResourceNotFoundException;

	List<FarmLeaseDTO> getFarmLeaseByTenant(String tenant) throws ResourceNotFoundException;

	List<FarmLeaseDTO> getFarmLeaseByType(String type) throws ResourceNotFoundException;

	FarmLeaseDTO updateFarmLease(Long id, FarmLeaseDTO farmLeaseDTO) throws ResourceNotFoundException;

}