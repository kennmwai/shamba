/**
 *
 */
package com.kenm.spring.farmleaseservice.mapper;

import java.util.List;

import com.kenm.spring.farmleaseservice.dto.FarmLeaseDTO;
import com.kenm.spring.farmleaseservice.entity.FarmLease;

/**
 * @author User
 *
 */
public interface FarmLeaseMapper {

	FarmLease toFarmLease(FarmLeaseDTO farmLeaseDTO);

	FarmLeaseDTO toFarmLeaseDTO(FarmLease farmLease);

	List<FarmLeaseDTO> toFarmLeaseDTOs(List<FarmLease> farmLeases);

	List<FarmLease> toFarmLeases(List<FarmLeaseDTO> farmLeaseDTOs);

}