/**
 *
 */
package com.kenm.spring.farmservice.mapper.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.kenm.spring.farmservice.dto.FarmDTO;
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
	public FarmDTO mapToFarmDetailsDTO(Farm farm) {
		FarmDTO farmDetails = new FarmDTO();
		BeanUtils.copyProperties(farm, farmDetails);
		return farmDetails;
	}

	@Override
	public Farm mapToFarm(FarmDTO farmDetails) {
		Farm farm = new Farm();
		BeanUtils.copyProperties(farmDetails, farm);
		return farm;
	}

}
