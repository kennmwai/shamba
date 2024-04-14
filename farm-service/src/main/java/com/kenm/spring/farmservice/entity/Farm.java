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

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String owner;

	@Column(nullable = false)
	private String location;

	@Column(nullable = false)
	private String type; // i.e Crop, Livestock, Mixed, Orchard

	@Column(nullable = false)
	private String status; // i.e. Active, Inactive

	@Column(nullable = false)
	private Integer size; // in Acres

	@Column(nullable = false)
	private Double pricePerAcre;

}
