package com.kenm.spring.farmleaseservice.dto.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateLeaseRequest {

	private Long farmId;
	private String leaseTenant;
	private String leaseType;
	private String leaseStatus;
	private Double leaseRent;
	private String leaseDuration;
	private String leaseStart;
	private String leaseEnd;
}
