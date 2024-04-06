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

import com.kenm.spring.farmservice.dto.payload.request.LeaseReqDTO;
import com.kenm.spring.farmservice.dto.payload.response.FarmLeaseDTO;


@FeignClient(name = "farm-lease-service", url = "${farm-lease-service.url}")
public interface LeaseServiceClient {

	@GetMapping("/api/v1/leases/all")
	List<FarmLeaseDTO> getLeases();

	@GetMapping("/api/v1/leases/farms/{farmId}")
	List<FarmLeaseDTO> getLeaseByFarmId(@PathVariable Long farmId);

	@GetMapping("/api/v1/leases/{leaseId}")
	FarmLeaseDTO getLeaseById(@PathVariable Long leaseId);

	@GetMapping("/api/v1/leases")
	List<FarmLeaseDTO> getLeaseByLeasedId(@RequestParam List<Long> ids);

	@PutMapping("/api/leases/{leaseId}")
	FarmLeaseDTO update(@PathVariable Long leaseId, @RequestBody FarmLeaseDTO LeaseDTO);

	@PostMapping(path = "/api/v1/leases", consumes = "application/json")
	FarmLeaseDTO create(@RequestBody LeaseReqDTO LeaseDTO);

	@DeleteMapping("/api/v1/leases/{leaseId}")
	void delete(@PathVariable Long leaseId);

}
