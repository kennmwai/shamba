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

	private Long id;

	@PositiveOrZero(message = "Farm ID is required")
	private Long farmId;

	@NotBlank(message = "Tenant Names is required")
	private String leaseTenant;

	@NotBlank(message = "Lease Type is required")
	private String leaseType;

	@NotBlank(message = "Lease Status is required")
	private String leaseStatus;

	@PositiveOrZero(message = "Rent Amount is required")
	private Double leaseRent;

	@NotBlank(message = "Lease Duration is required")
	private String leaseDuration;

	@NotBlank(message = "Lease Start Date is required")
	private String leaseStart;

	@NotBlank(message = "Lease End Date is required")
	private String leaseEnd;

	private List<GetPaymentInfoResponse> payments;
}