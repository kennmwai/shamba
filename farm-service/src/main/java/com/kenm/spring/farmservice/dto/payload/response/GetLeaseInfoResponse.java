package com.kenm.spring.farmservice.dto.payload.response;

import java.io.Serializable;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetLeaseInfoResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long leaseId;
	private Long farmId;
	private String leaseTenant;
	private String leaseType;
	private String leaseStatus;
	private Double leaseRent;
	private String leaseDuration;
	private String leaseStart;
	private String leaseEnd;
	private List<GetPaymentInfoResponse> payments;
}