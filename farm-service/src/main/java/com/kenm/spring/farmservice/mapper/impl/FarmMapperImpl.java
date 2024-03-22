/**
 *
 */
package com.kenm.spring.farmservice.mapper.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.kenm.spring.farmservice.dto.FarmDetailsDTO;
import com.kenm.spring.farmservice.entity.Farm;
import com.kenm.spring.farmservice.mapper.FarmMapper;

import lombok.RequiredArgsConstructor;

/**
 * @author User
 *
 */
@Component
@RequiredArgsConstructor
public class FarmMapperImpl implements FarmMapper {


	@Override
	public FarmDetailsDTO mapToFarmDetailsDTO(Farm farm) {
		FarmDetailsDTO farmDetails = new FarmDetailsDTO();
		BeanUtils.copyProperties(farm, farmDetails);
		return farmDetails;
	}

	@Override
	public Farm mapToFarm(FarmDetailsDTO farmDetails) {
		Farm farm = new Farm();
		BeanUtils.copyProperties(farmDetails, farm);
		return farm;
	}

}
