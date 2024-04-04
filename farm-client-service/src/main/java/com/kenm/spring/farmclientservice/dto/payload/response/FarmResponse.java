package com.kenm.spring.farmclientservice.dto.payload.response;

import java.util.List;

import com.kenm.spring.farmclientservice.dto.payload.FarmDTO;
import com.kenm.spring.farmclientservice.dto.payload.LeaseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author User
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FarmResponse {
	private FarmDTO farm;
	private List<LeaseDTO> lease;

}
