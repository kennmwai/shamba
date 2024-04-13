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

	private Long id;
	private String name;
	private String owner;
	private String location;
	private String type;
	private String status;
	private Integer size;
	private Double pricePerAcre;
}
