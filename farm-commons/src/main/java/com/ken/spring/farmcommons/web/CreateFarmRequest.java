package com.ken.spring.farmcommons.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateFarmRequest {

	@NotBlank(message = "Farm name cannot be blank")
	@Size(min = 1, max = 255)
	private String name;

	@NotBlank(message = "Farm {owner} cannot be blank")
	@Size(min = 1, max = 255)
	private String owner;

	@NotBlank(message = "Farm {location} cannot be blank")
	@Size(min = 1, max = 255)
	private String location;

	@NotBlank(message = "Farm {type} cannot be blank")
	@Size(min = 1, max = 255)
	private String type;

	@NotBlank(message = "Farm {status} cannot be blank")
	@Size(min = 1, max = 255)
	private String status;

	@Positive(message = "farm {size} in Acres must be greater than or equal to 1")
	private Integer size;

	@PositiveOrZero(message = "Farm {pricePerAcre} must be greater than or equal to 0")
	private Double pricePerAcre;

}
