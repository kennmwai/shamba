package com.kenm.spring.farmclientservice.dto.payload.response;

import java.util.List;

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
