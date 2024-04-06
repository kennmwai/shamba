/**
 *
 */
package com.kenm.spring.farmservice.mapper.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.kenm.spring.farmservice.dto.FarmDTO;
import com.kenm.spring.farmservice.dto.payload.request.FarmReqDTO;
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
	public Farm mapToFarm(FarmDTO farmDto) {
		Farm farm = new Farm();
		BeanUtils.copyProperties(farmDto, farm);
		return farm;
	}

	@Override
	public Farm mapToFarm(FarmReqDTO farmReqDto) {
		Farm farm = new Farm();
		BeanUtils.copyProperties(farmReqDto, farm);
		return farm;		
	}

	@Override
	public FarmDTO mapToFarmDTO(Farm farm) {
		FarmDTO farmDetails = new FarmDTO();
		BeanUtils.copyProperties(farm, farmDetails);
		return farmDetails;
	}

	@Override
	public List<FarmDTO> mapToFarmDTOs(List<Farm> farms) {
		return farms.parallelStream().map(this::mapToFarmDTO)
					.collect(Collectors.toList());
	}

	@Override
	public List<Farm> mapToFarms(List<FarmDTO> farmDTOs) {
		return farmDTOs.parallelStream().map(this::mapToFarm)
					.collect(Collectors.toList());
	}

}
