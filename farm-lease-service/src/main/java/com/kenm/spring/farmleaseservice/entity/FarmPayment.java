package com.kenm.spring.farmleaseservice.entity;

import java.time.LocalDate;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "farm_payments")
public class FarmPayment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "payment_Status", nullable = false)
	private String paymentStatus;

	@Column(name = "payment_date", nullable = false)
	private LocalDate paymentDate;

	@Column(name = "payment_Amount", nullable = false)
	private Double paymentAmount;

	@Column(name = "payment_method", nullable = false)
	private String paymentMethod;

	@Column(name = "payment_receipt", nullable = false)
	private Long paymentReceipt;

	@Column(name = "payment_notes", nullable = false)
	private String paymentNotes;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "lease_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private FarmLease farmLease;

}
