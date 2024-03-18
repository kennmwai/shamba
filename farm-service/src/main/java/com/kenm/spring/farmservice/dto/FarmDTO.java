/**
 * FarmDTO.java - DTO class for Farm
 */
package com.kenm.spring.farmservice.dto;

/**
 * @author User
 *
 */
import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor @Getter @Setter
@EqualsAndHashCode
@ToString
public class FarmDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@NotBlank(message = "Farm name cannot be blank")
	@Size(min = 1, max = 255)
	private String farmName;
	
	@NotBlank(message = "Farm address cannot be blank")
	@Size(min = 1, max = 255)
	private String farmAddress;
	
	@NotBlank(message = "Farm Type cannot be blank")
	@Size(min = 1, max = 255)
	private String farmType;
	
	@NotBlank(message = "Farm Status cannot be blank")
	@Size(min = 1, max = 255)
	private String farmStatus;

	@Positive(message = "farm Size (Acres) must be greater than or equal to 1")
	private Integer farmSize;
	
	@PositiveOrZero(message = "Price per acre must be greater than or equal to 0")
	private Double pricePerAcre;

	@NotBlank(message = "Farm Owner cannot be blank")
	@Size(min = 1, max = 255)
	private String farmOwner;

	private Long leaseId; // FarmLease entity Id
}
