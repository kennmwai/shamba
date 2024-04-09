package com.kenm.spring.farmservice.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.kenm.spring.farmservice.dto.payload.request.CreateLeaseReq;
import com.kenm.spring.farmservice.dto.payload.response.GetLeaseInfoResponse;


@FeignClient(name = "farm-lease-service", url = "${farm-lease-service.url}")
public interface LeaseServiceClient {

	@GetMapping("/api/v1/leases/all")
	List<GetLeaseInfoResponse> getLeases();

	@GetMapping("/api/v1/leases/farms/{farmId}")
	List<GetLeaseInfoResponse> getLeaseByFarmId(@PathVariable Long farmId);

	@GetMapping("/api/v1/leases/{leaseId}")
	GetLeaseInfoResponse getLeaseById(@PathVariable Long leaseId);

	@GetMapping("/api/v1/leases")
	List<GetLeaseInfoResponse> getLeaseByLeasedId(@RequestParam List<Long> ids);

	@PutMapping("/api/leases/{leaseId}")
	GetLeaseInfoResponse update(@PathVariable Long leaseId, @RequestBody GetLeaseInfoResponse LeaseDTO);

	@PostMapping(path = "/api/v1/leases", consumes = "application/json")
	GetLeaseInfoResponse create(@RequestBody CreateLeaseReq LeaseDTO);

	@DeleteMapping("/api/v1/leases/{leaseId}")
	void delete(@PathVariable Long leaseId);

}
