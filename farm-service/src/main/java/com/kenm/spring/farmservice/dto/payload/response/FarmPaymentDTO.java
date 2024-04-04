package com.kenm.spring.farmservice.dto.payload.response;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FarmPaymentDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String paymentStatus;
	private Double paymentAmount;
	private String paymentMethod;
	private Long paymentReceipt;
	private LocalDate paymentDate;
	private String paymentNotes;

}
