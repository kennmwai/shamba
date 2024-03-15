/**
 * 
 */
package com.kenm.spring.farmleaseservice.mapper.impl;

import org.springframework.beans.BeanUtils;

import com.kenm.spring.farmleaseservice.dto.FarmLeaseDTO;
import com.kenm.spring.farmleaseservice.entity.FarmLease;
import com.kenm.spring.farmleaseservice.mapper.FarmLeaseMapper;

/**
 * @author User
 *
 */
public class FarmLeaseMapperImpl implements FarmLeaseMapper {

	@Override
	public FarmLeaseDTO toFarmLeaseDTO(FarmLease farmLease) {
		FarmLeaseDTO farmLeaseDTO = new FarmLeaseDTO();
		BeanUtils.copyProperties(farmLease, farmLeaseDTO);
		return farmLeaseDTO;
	}

	@Override
	public FarmLease toFarmLease(FarmLeaseDTO farmLeaseDTO) {
		FarmLease farmLease = new FarmLease();
		BeanUtils.copyProperties(farmLeaseDTO, farmLease);
		return farmLease;
	}

}