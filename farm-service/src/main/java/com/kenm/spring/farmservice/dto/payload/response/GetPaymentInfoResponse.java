package com.kenm.spring.farmservice.dto.payload.response;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetPaymentInfoResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long paymentId;
	private String paymentStatus;
	private Double paymentAmount;
	private String paymentMethod;
	private Long paymentReceipt;
	private LocalDate paymentDate;
	private String paymentNotes;

}
