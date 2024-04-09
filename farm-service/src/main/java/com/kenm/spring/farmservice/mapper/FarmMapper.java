/**
 *
 */
package com.kenm.spring.farmservice.mapper;

import java.util.List;

import com.kenm.spring.farmservice.dto.FarmDTO;
import com.kenm.spring.farmservice.dto.payload.request.CreateFarmReq;
import com.kenm.spring.farmservice.entity.Farm;

/**
 * @author User
 *
 */
public interface FarmMapper {

	Farm mapToFarm(FarmDTO farmDTO);

	Farm mapToFarm(CreateFarmReq createFarmReq);

	FarmDTO mapToFarmDTO(Farm farm);

	List<FarmDTO> mapToFarmDTOs(List<Farm> farms);

	List<Farm> mapToFarms(List<FarmDTO> farmDTOs);

}