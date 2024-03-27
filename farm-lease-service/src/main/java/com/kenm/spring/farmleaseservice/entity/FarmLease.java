/**
 *
 */
package com.kenm.spring.farmleaseservice.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

	@Column(name = "farm_id", nullable = false)
	private Long farmId;

	@Column(name = "lease_tenant", nullable = false)
	private String leaseTenant;

	@Column(name = "lease_type", nullable = false)
	private String leaseType; // (short-term lease, long-term lease, lease-to-own).

	@Column(name = "lease_Status", nullable = false)
	private String leaseStatus; // (active, expired, terminated).

	@Column(name = "lease_rent", nullable = false)
	private Double leaseRent;

	@Column(name = "lease_duration", nullable = false)
	private String leaseDuration;

	@Column(name = "lease_start", nullable = false)
	private String leaseStart;

	@Column(name = "lease_end", nullable = false)
	private String leaseEnd;

	@OneToMany(mappedBy = "farmLease", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<FarmPayment> payments;

}