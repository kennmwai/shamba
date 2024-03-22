/**
 *
 */
package com.kenm.spring.farmleaseservice.dto;

/**
 * @author User
 *
 */
import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class FarmLeaseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@PositiveOrZero(message = "Farm ID is required")
    private Long farmId;

	@NotBlank(message = "Tenant Names is required")
	private String tenant;

	@NotBlank(message = "Lease Type is required")
    private String type;
	
	@PositiveOrZero(message = "Rent Amount is required")
	private Double rent;
	
	@NotBlank(message = "Lease Duration is required")
	private String duration;
	
	@NotBlank(message = "Lease Status is required")
	private String status;

}