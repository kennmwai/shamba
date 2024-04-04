package com.kenm.spring.farmservice.dto.payload.request;

import java.io.Serializable;
import java.time.LocalDate;

import com.kenm.spring.farmservice.dto.validation.ValidIsoDate;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author User
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentReqDTO implements Serializable {

	private static final long serialVersionUID = 1L;

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