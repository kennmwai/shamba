package com.kenm.spring.farmclientservice.dto.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FarmDTO {

	private Long id;
	private String name;
	private String owner;
	private String location;
	private String type;
	private String status;
	private Integer size;
	private Double pricePerAcre;

}