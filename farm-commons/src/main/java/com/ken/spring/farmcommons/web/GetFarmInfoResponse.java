package com.ken.spring.farmcommons.web;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetFarmInfoResponse {

	private Long id;
	private UUID farmId;
	private String farmName;
	private String farmOwner;
	private String farmLocation;
	private String farmType;
	private String farmStatus;
	private Integer farmSize;
	private Double pricePerAcre;

	private LocalDateTime createdAt;
	private String createdBy;
	private LocalDateTime lastModifiedAt;
	private String lastModifiedBy;
}
