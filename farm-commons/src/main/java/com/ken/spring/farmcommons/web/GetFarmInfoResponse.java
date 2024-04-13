package com.ken.spring.farmcommons.web;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetFarmInfoResponse {

	private Long id;
	private String name;
	private String owner;
	private String location;
	private String type;
	private String status;
	private Integer size;
	private Double pricePerAcre;
	private LocalDateTime createdAt;
	private String createdBy;
	private LocalDateTime lastModifiedAt;
	private String lastModifiedBy;
}
