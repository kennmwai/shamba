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
	public FarmDTO toFarmDTO(Farm farm) {
		FarmDTO farmDTO = new FarmDTO();
		BeanUtils.copyProperties(farm, farmDTO);
		return farmDTO;
	}

	@Override
	public Farm toFarm(FarmDTO farmDTO) {
		Farm farm = new Farm();
		BeanUtils.copyProperties(farmDTO, farm);
		return farm;
	}

}
