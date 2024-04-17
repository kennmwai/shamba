package com.kenm.spring.farmclientservice.dto.payload.request;

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

	private String name;
	private String owner;
	private String location;
	private String type;
	private String status;
	private Integer size;
	private Double pricePerAcre;

}