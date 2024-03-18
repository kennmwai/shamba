/**
 * Farm.java - Entity class for Farm
 */
package com.kenm.spring.farmservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author User
 *
 */
@Entity
@Table(name = "farms")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Farm {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String farmName;

	@Column(nullable = false)
	private String farmAddress;

	@Column(nullable = false)
	private String farmType; // i.e Crop, Livestock, Orchard

	@Column(nullable = false)
	private String farmStatus; // i.e. Active, Inactive

	@Column(nullable = false)
	private Integer farmSize; // in Acres

	@Column(nullable = false)
	private String farmOwner;

	@Column(nullable = false)
	private Double pricePerAcre;

	@Column(nullable = false)
	private Long leaseId = 0L;  // Corresponds to the id field in FarmLease entity set default value to zero.
}