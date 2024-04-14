/**
 * FarmDTO.java - DTO class for Farm
 */
package com.kenm.spring.farmservice.dto;

/**
 * @author User
 *
 */
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import com.kenm.spring.farmservice.entity.Amenities;
import com.kenm.spring.farmservice.entity.Links;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FarmDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private UUID farmId;
	private String farmName;
	private String farmOwner;
	private String farmLocation;
	private String farmType;
	private String farmStatus;
	private Integer farmSize;
	private Double pricePerAcre;

    private List<Amenities> farmFeatures;
    private List<Links> links;
}
