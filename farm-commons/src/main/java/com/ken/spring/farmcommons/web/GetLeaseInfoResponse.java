package com.ken.spring.farmcommons.web;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetLeaseInfoResponse {

	private Long id;
	private Long farmId;
	private String leaseTenant;
	private String leaseType;
	private String leaseStatus;
	private Double leaseRent;
	private String leaseDuration;
	private String leaseStart;
	private String leaseEnd;
	private List<GetPaymentInfoResponse> payments;
	private LocalDateTime createdAt;
	private String createdBy;
	private LocalDateTime lastModifiedAt;
	private String lastModifiedBy;
}
