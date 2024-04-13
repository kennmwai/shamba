package com.ken.spring.farmcommons.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ken.spring.farmcommons.web.CreateLeaseRequest;
import com.ken.spring.farmcommons.web.CreatePaymentRequest;
import com.ken.spring.farmcommons.web.GetLeaseInfoResponse;
import com.ken.spring.farmcommons.web.GetPaymentInfoResponse;
import com.ken.spring.farmcommons.web.UpdateLeaseRequest;
import com.ken.spring.farmcommons.web.UpdatePaymentRequest;


@FeignClient("farm-lease-service")
public interface LeaseClient {

	@GetMapping("/api/v1/leases/all")
	List<GetLeaseInfoResponse> getLeases();

	@GetMapping("/api/v1/leases/farms/{farmId}")
	List<GetLeaseInfoResponse> getLeaseByFarmId(@PathVariable Long farmId);

	@GetMapping("/api/v1/leases/{leaseId}")
	GetLeaseInfoResponse getLeaseById(@PathVariable Long leaseId);

	@GetMapping("/api/v1/leases")
	List<GetLeaseInfoResponse> getLeaseByLeasedId(@RequestParam List<Long> ids);

	@PutMapping("/api/leases/{leaseId}")
	GetLeaseInfoResponse update(@PathVariable Long leaseId, @RequestBody UpdateLeaseRequest updateLeaseRequest);

	@PostMapping(path = "/api/v1/leases", consumes = "application/json")
	GetLeaseInfoResponse create(@RequestBody CreateLeaseRequest createLeaseRequest);

	@DeleteMapping("/api/v1/leases/{leaseId}")
	void delete(@PathVariable Long leaseId);

	// Payments

	@GetMapping("/api/v1/leases/payments/all")
	List<GetPaymentInfoResponse> payments();

	@GetMapping("/api/v1/leases/{leaseId}/payments")
	List<GetPaymentInfoResponse> getPaymentsByLeaseId(@PathVariable Long paymentId);

	@GetMapping("/api/v1/leases/payments/{paymentId}")
	GetPaymentInfoResponse getPaymentById(@PathVariable Long paymentId);

	@GetMapping("/api/v1/leases/payments")
	List<GetLeaseInfoResponse> getPaymentsByPaymentIds(@RequestParam List<Long> ids);

	@PutMapping("/api/leases/payments/{paymentId}")
	GetPaymentInfoResponse updatePayment(@PathVariable Long paymentId, @RequestBody UpdatePaymentRequest payment);

	@PostMapping(path = "/api/v1/leases/payments", consumes = "application/json")
	GetPaymentInfoResponse create(@RequestBody CreatePaymentRequest createPaymentRequest);

	@DeleteMapping("/api/v1/leases/payments/{paymentId}")
	void deletePayment(@PathVariable Long paymentId);

}