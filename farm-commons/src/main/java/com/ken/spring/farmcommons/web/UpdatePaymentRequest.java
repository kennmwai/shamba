package com.ken.spring.farmcommons.web;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdatePaymentRequest {

	private String paymentStatus;
	private Double paymentAmount;
	private String paymentMethod;
	private Long paymentReceipt;
	private LocalDate paymentDate;
	private String paymentNotes;
}
