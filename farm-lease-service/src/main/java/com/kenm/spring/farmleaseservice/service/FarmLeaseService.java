/**
 *
 */
package com.kenm.spring.farmleaseservice.service;

import java.util.List;

import org.springframework.lang.NonNull;

import com.kenm.spring.farmleaseservice.dto.FarmLeaseDTO;
import com.kenm.spring.farmleaseservice.dto.payload.CreateLeaseRequest;
import com.kenm.spring.farmleaseservice.dto.payload.UpdateLeaseRequest;
import com.kenm.spring.farmleaseservice.exception.ResourceNotFoundException;

/**
 * @author User
 *
 */
public interface FarmLeaseService {
	long count();

	boolean exists(@NonNull Long id);

//	FarmLeaseDTO findByFarmId(Long id) throws ResourceNotFoundException;

	void deleteAll() throws ResourceNotFoundException;

	void deleteByIds(@NonNull Iterable<Long> ids) throws ResourceNotFoundException;

	void delete(@NonNull Long id) throws ResourceNotFoundException;

	List<FarmLeaseDTO> findAll();

	List<FarmLeaseDTO> findAllById(@NonNull Iterable<Long> ids) throws ResourceNotFoundException;

	FarmLeaseDTO findById(@NonNull Long id) throws ResourceNotFoundException;

	List<FarmLeaseDTO> getFarmLeaseByFarmId(@NonNull Long id) throws ResourceNotFoundException;

	List<FarmLeaseDTO> getFarmLeaseByStatus(String status) throws ResourceNotFoundException;

	List<FarmLeaseDTO> getFarmLeaseByTenant(String tenant) throws ResourceNotFoundException;

	List<FarmLeaseDTO> getFarmLeaseByType(String type) throws ResourceNotFoundException;

	FarmLeaseDTO createFarmLease(CreateLeaseRequest createLease);

	FarmLeaseDTO updateFarmLease(@NonNull Long id, UpdateLeaseRequest updateLease) throws ResourceNotFoundException;

}