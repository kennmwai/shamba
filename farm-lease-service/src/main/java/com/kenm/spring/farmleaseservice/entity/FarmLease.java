/**
 *
 */
package com.kenm.spring.farmleaseservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author User
 *
 */
@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name = "farm_leases")
public class FarmLease {
	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lease_seq")
    @SequenceGenerator(name = "lease_seq", sequenceName = "lease_sequence", allocationSize = 1, initialValue = 100)
    private Long id;

    @Column(nullable = false)
    private Long farmId;

	@Column(nullable = false)
	private String tenant;

	@Column(nullable = false)
    private String type;  // (short-term lease, long-term lease, lease-to-own).

	@Column(nullable = false)
	private Double rent;

	@Column(nullable = false)
	private String duration;

	@Column(nullable = false)
	private String status;  // (active, expired, terminated).

}