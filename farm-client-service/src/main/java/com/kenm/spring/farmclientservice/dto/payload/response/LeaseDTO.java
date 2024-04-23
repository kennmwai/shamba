package com.kenm.spring.farmclientservice.dto.payload.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaseDTO  {

	private Long leaseId;
	private Long farmId;
	private String leaseTenant;
	private String leaseType;
	private String leaseStatus;
	private Double leaseRent;
	private String leaseDuration;
	private String leaseStart;
	private String leaseEnd;
	private List<PaymentDTO> payments;

}