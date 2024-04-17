package com.kenm.spring.farmclientservice.dto.payload.request;

import java.util.List;

import com.kenm.spring.farmclientservice.dto.payload.response.PaymentDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaseRequest {

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
