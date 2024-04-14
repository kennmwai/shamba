package com.kenm.spring.farmclientservice.dto.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
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
public class FarmRequest {

	@NotBlank(message = "FarmName cannot be blank")
	@Size(min = 1, max = 255)
	private String farmName;

	@NotBlank(message = "FarmOwner cannot be blank")
	@Size(min = 1, max = 255)
	private String farmOwner;

	@NotBlank(message = "FarmLocation cannot be blank")
	@Size(min = 1, max = 255)
	private String farmLocation;

	@NotBlank(message = "FarmType cannot be blank")
	@Size(min = 1, max = 255)
	private String farmType;

	@NotBlank(message = "FarmStatus cannot be blank")
	@Size(min = 1, max = 255)
	private String farmStatus;

	@Positive(message = "farmSize (Acres) must be greater than or equal to 1")
	private Integer farmSize;

	@PositiveOrZero(message = "PricePerAcre must be greater than or equal to 0")
	private Double pricePerAcre;


}