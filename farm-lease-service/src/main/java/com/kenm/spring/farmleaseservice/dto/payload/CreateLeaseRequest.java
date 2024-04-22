package com.kenm.spring.farmleaseservice.dto.payload;

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
public class CreateLeaseRequest {

	@PositiveOrZero(message = "Lease Farm Id {farmId} is required")
	private Long farmId;

	@NotBlank(message = "Lrease Tenant {leaseTenant} Names is required")
	private String leaseTenant;

	@NotBlank(message = "LeaseType is required")
	private String leaseType;

	@NotBlank(message = "LeaseStatus is required")
	private String leaseStatus;

	@PositiveOrZero(message = "Rent Amount {leaseRent} is required")
	private Double leaseRent;

	@NotBlank(message = "leaseDuration is required")
	private String leaseDuration;

	@NotBlank(message = "leaseStart Date is required")
	private String leaseStart;

	@NotBlank(message = "leaseEnd Date is required")
	private String leaseEnd;

}