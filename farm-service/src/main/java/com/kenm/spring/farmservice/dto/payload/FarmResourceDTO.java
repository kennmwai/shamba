package com.kenm.spring.farmservice.dto.payload;

import java.util.List;

import com.kenm.spring.farmservice.dto.FarmDTO;
import com.kenm.spring.farmservice.dto.payload.response.GetLeaseInfoResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FarmResourceDTO {
	private FarmDTO farm;
	private List<GetLeaseInfoResponse> lease;

	// private List<Link> links;

}
