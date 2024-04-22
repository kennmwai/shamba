package com.ken.spring.farmcommons.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateFarmRequest {

	private String farmName;
	private String farmOwner;
	private String farmLocation;
	private String farmType;
	private String farmStatus;
	private Integer farmSize;
	private Double pricePerAcre;
}
