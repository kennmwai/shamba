/**
 *
 */
package com.kenm.spring.farmservice.mapper;

import java.util.List;

import org.springframework.lang.NonNull;

import com.kenm.spring.farmservice.dto.FarmDTO;
import com.kenm.spring.farmservice.entity.Farm;

/**
 * @author User
 *
 */
public interface FarmMapper {

	Farm mapToFarm(@NonNull FarmDTO farmDTO);

	FarmDTO mapToFarmDTO(@NonNull Farm farm);

	List<FarmDTO> mapToFarmDTOs(@NonNull List<Farm> farms);

	List<Farm> mapToFarms(@NonNull List<FarmDTO> farmDTOs);

}