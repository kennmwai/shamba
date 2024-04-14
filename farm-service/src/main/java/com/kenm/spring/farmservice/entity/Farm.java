/**
 * Farm.java - Entity class for Farm
 */
package com.kenm.spring.farmservice.entity;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author User
 *
 */
@Entity
@Table(name = "farms")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Farm {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @Column(name = "farm_id", nullable = false, unique = true, updatable = false)
    @Builder.Default
    private UUID farmId = UUID.nameUUIDFromBytes(("farm-details-" + UUID.randomUUID()).getBytes(StandardCharsets.UTF_8));

	@Column(name = "farm_name", nullable = false)
	private String farmName;

	@Column(name = "farm_owner", nullable = false)
	private String farmOwner;

	@Column(name = "farm_location", nullable = false)
	private String farmLocation;

	@Column(name = "farm_type", nullable = false)
	private String farmType; // i.e Crop, Livestock, Mixed, Orchard

	@Column(name = "farm_status", nullable = false)
	private String farmStatus; // i.e. Active, Inactive

	@Column(name = "farm_size", nullable = false)
	private Integer farmSize; // in Acres

	@Column(nullable = false)
	private Double pricePerAcre;

    @ElementCollection
    private List<Amenities> farmFeatures;

    @ElementCollection
    private List<Links> links;

}
