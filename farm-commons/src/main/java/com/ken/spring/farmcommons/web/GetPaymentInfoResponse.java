package com.ken.spring.farmcommons.web;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPaymentInfoResponse {

	private Long paymentId;
	private String paymentStatus;
	private Double paymentAmount;
	private String paymentMethod;
	private Long paymentReceipt;
	private LocalDate paymentDate;
	private String paymentNotes;
	private LocalDateTime createdAt;
	private String createdBy;
	private LocalDateTime lastModifiedAt;
	private String lastModifiedBy;
}
