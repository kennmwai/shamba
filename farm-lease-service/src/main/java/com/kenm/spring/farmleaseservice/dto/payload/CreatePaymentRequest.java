package com.kenm.spring.farmleaseservice.dto.payload;


import java.time.LocalDate;

import com.kenm.spring.farmleaseservice.dto.validation.ValidIsoDate;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePaymentRequest {

	@NotBlank(message = "Lease {paymentStatus} can not be null.")
	private String paymentStatus;

	@NotBlank(message = "Lease {paymentAmount} can not be null.")
	private Double paymentAmount;

	@NotBlank(message = "Lease {paymentMethod} can not be null.")
	private String paymentMethod;

	@NotBlank(message = "Lease {paymentReceipt} can not be null.")
	private Long paymentReceipt;

	@NotBlank(message = "Lease {paymentDate} can not be null.")
	@ValidIsoDate
	private LocalDate paymentDate;

	private String paymentNotes;

}