/**
 *
 */
package com.kenm.spring.farmservice.mapper.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.lang.NonNull;
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
	public Farm mapToFarm(@NonNull FarmDTO farmDetails) {
		Farm farm = new Farm();
		BeanUtils.copyProperties(farmDetails, farm);
		return farm;
	}

	@Override
	public FarmDTO mapToFarmDTO(@NonNull Farm farm) {
		FarmDTO farmDetails = new FarmDTO();
		BeanUtils.copyProperties(farm, farmDetails);
		return farmDetails;
	}

	@Override
	public List<FarmDTO> mapToFarmDTOs(@NonNull List<Farm> farms) {
		return farms.parallelStream().map(this::mapToFarmDTO)
					.collect(Collectors.toList());
	}

	@Override
	public List<Farm> mapToFarms(@NonNull List<FarmDTO> farmDTOs) {
		return farmDTOs.parallelStream().map(this::mapToFarm)
					.collect(Collectors.toList());
	}

}
