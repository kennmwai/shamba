/**
 * 
 */
package com.kenm.spring.farmleaseservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kenm.spring.farmleaseservice.entity.FarmPayment;
import com.kenm.spring.farmleaseservice.repository.FarmPaymentRepository;

/**
 * @author User
 *
 */
@RestController
@RequestMapping(path = "/api/v1/leases", produces = "application/json")
public class PaymentController {

	@Autowired
	private FarmPaymentRepository farmPaymentRepository;

	@GetMapping("/payments")
	public ResponseEntity<List<FarmPayment>> getLeasePayments() {
		List<FarmPayment> payments = farmPaymentRepository.findAll();
		return new ResponseEntity<>(payments, HttpStatus.OK);
	}

	// TODO: POST /payments
	// TODO: PUT /payments
	// TODO: DELETE /payments

}
