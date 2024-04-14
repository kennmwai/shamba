package com.kenm.spring.farmclientservice.dto.payload.response;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FarmDTO {

	private Long id;
	private UUID farmId;
	private String farmName;
	private String farmOwner;
	private String farmLocation;
	private String farmType;
	private String farmStatus;
	private Integer farmSize;
	private Double pricePerAcre;

}