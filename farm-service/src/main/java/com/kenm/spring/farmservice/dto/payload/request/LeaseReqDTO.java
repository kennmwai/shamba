package com.kenm.spring.farmservice.dto.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
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
public class LeaseReqDTO {

	@NotBlank(message = "Lease Farm Id {farmId} is required")
	private Long farmId;

	@NotBlank(message = "Lrease Tenant {leaseTenant} Names is required")
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

}
