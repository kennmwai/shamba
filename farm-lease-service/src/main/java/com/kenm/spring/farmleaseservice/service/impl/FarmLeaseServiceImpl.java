/**
 *
 */
package com.kenm.spring.farmleaseservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kenm.spring.farmleaseservice.FarmLease;
import com.kenm.spring.farmleaseservice.repository.FarmLeaseRepository;
import com.kenm.spring.farmleaseservice.service.FarmLeaseService;

/**
 * @author User
 *
 */
@Service
public class FarmLeaseServiceImpl implements FarmLeaseService {

	@Autowired
	private FarmLeaseRepository farmLeaseRepository;

	@Override
	public List<FarmLease> findAll() {
		return farmLeaseRepository.findAll();
	}

	@Override
	public FarmLease findById(Long id) {
		return farmLeaseRepository.findById(id).orElse(null);
	}

	@Override
	public FarmLease createFarmLease(FarmLease farmLease) {
		return farmLeaseRepository.save(farmLease);
	}

	@Override
	public FarmLease updateFarmLease(FarmLease farmLease) {
		if (farmLease == null || farmLease.getId() == null) {
			throw new IllegalArgumentException("Entity must not be null");
		}
		return farmLeaseRepository.save(farmLease);
	}

	@Override
	public void deleteById(Long id) {
		farmLeaseRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		farmLeaseRepository.deleteAll();
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return farmLeaseRepository.existsById(id);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return farmLeaseRepository.count();
	}

	@Override
	public List<FarmLease> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return farmLeaseRepository.findAllById(ids);
	}

	@Override
	public void deleteAllById(Iterable<Long> ids) {
		farmLeaseRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends FarmLease> entities) {
		farmLeaseRepository.deleteAll(entities);
	}
}
