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
 public FarmLease save(FarmLease farmLease) {
     return farmLeaseRepository.save(farmLease);
 }

 @Override
 public void deleteById(Long id) {
     farmLeaseRepository.deleteById(id);
 }
}
