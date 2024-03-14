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

 FarmLease save(FarmLease farmLease);

 void deleteById(Long id);
}
