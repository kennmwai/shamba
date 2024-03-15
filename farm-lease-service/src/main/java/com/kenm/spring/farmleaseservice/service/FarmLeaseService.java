/**
 *
 */
package com.kenm.spring.farmleaseservice.service;

import java.util.List;

import com.kenm.spring.farmleaseservice.FarmLease;

/**
 * @author User
 *
 */
public interface FarmLeaseService {
	List<FarmLease> findAll();

	FarmLease findById(Long id);

	FarmLease createFarmLease(FarmLease farmLease);

	FarmLease updateFarmLease(FarmLease farmLease);

	void deleteById(Long id);

	boolean existsById(Long id);

	long count();

	List<FarmLease> findAllById(Iterable<Long> ids);

	void deleteAllById(Iterable<Long> ids);

	void deleteAll(Iterable<? extends FarmLease> entities);

	void deleteAll();
}
