/**
 *
 */
package com.kenm.spring.farmservice.mapper;

import com.kenm.spring.farmservice.dto.FarmDetailsDTO;
import com.kenm.spring.farmservice.entity.Farm;

/**
 * @author User
 *
 */
public interface FarmMapper {

	FarmDetailsDTO mapToFarmDetailsDTO(Farm farm);

	Farm mapToFarm(FarmDetailsDTO farmDTO);

}