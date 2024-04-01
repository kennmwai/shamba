/**
 *
 */
package com.kenm.spring.farmservice.mapper;

import com.kenm.spring.farmservice.dto.FarmDTO;
import com.kenm.spring.farmservice.entity.Farm;

/**
 * @author User
 *
 */
public interface FarmMapper {

	FarmDTO mapToFarmDetailsDTO(Farm farm);

	Farm mapToFarm(FarmDTO farmDTO);

}