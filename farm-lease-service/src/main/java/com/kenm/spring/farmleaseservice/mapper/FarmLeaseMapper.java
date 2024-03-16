/**
 *
 */
package com.kenm.spring.farmleaseservice.mapper;

import com.kenm.spring.farmleaseservice.dto.FarmLeaseDTO;
import com.kenm.spring.farmleaseservice.entity.FarmLease;

/**
 * @author User
 *
 */
public interface FarmLeaseMapper {

	FarmLeaseDTO toFarmLeaseDTO(FarmLease farmLease);

	FarmLease toFarmLease(FarmLeaseDTO farmLeaseDTO);

}