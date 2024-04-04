package com.kenm.spring.farmclientservice.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.kenm.spring.farmclientservice.dto.payload.FarmDTO;
import com.kenm.spring.farmclientservice.dto.payload.LeaseDTO;
import com.kenm.spring.farmclientservice.dto.payload.request.FarmRequest;
import com.kenm.spring.farmclientservice.dto.payload.request.LeaseRequest;
import com.kenm.spring.farmclientservice.dto.payload.response.FarmResponse;

import jakarta.validation.Valid;

/**
 * @author User
 *
 */
@FeignClient(name = "farm-service", url = "${farm-service.url}")
public interface FarmClient {

	@GetMapping("/api/v1/farms/all")
	List<FarmResponse> getAllFarms();

	@GetMapping("/api/v1/farms")
	List<FarmResponse> getFarmsByIds(@RequestParam List<Long> farmIds);

	@GetMapping("/api/v1/farms/{farmId:\\d+}")
	FarmResponse getFarmById(@PathVariable Long farmId);

	@GetMapping("/api/v1/farms/price/{farmId}")
	public double calculateTotalPrice(@PathVariable Long farmId);

	@GetMapping("/api/v1/farms/exists/{farmId}")
	public boolean existsFarmById(@PathVariable  Long farmId);

	@GetMapping("/api/v1/farms/count")
	public long countFarms();

	@PostMapping("/api/v1/farms")
	FarmDTO createFarm(@RequestBody FarmRequest farm);

	@PutMapping("/api/v1/farms/{farmId}")
	FarmDTO updateFarm(@PathVariable Long farmId, @RequestBody FarmRequest farm);

	@DeleteMapping("/api/v1/farms/{farmId}")
	void deleteFarm(@PathVariable Long farmId);

	@DeleteMapping("/api/v1/farms")
	void deleteByIds(@RequestParam List<Long> farmIds);

	@DeleteMapping("/api/v1/farms/all")
	void deleteAllFarms();
	
	// Lease

	@GetMapping("/api/v1/farms/{farmId}/leases")
	List<LeaseDTO> getLeaseByFarmId(@PathVariable Long farmId);

	@GetMapping("/api/v1/farms/leases/{leaseId}")
	LeaseDTO getLeaseByLeasedId(@PathVariable Long leaseId);

	@GetMapping("/api/v1/farms/leases/all")
	List<LeaseDTO> getAllLeases();

	@GetMapping("/api/v1/farms/leases")
	List<LeaseDTO> getLeaseByLeasedIds(@RequestParam List<Long> leaseIds);
	
	@PostMapping("/api/v1/farms/leases")
	LeaseDTO createLease(@Valid @RequestBody LeaseRequest LeaseReqDTO);

	@PutMapping("/api/v1/farms/leases/{leaseId}")
	LeaseDTO updateLease(@PathVariable Long leaseId, @RequestBody LeaseRequest LeaseReqDTO);

	@DeleteMapping("/api/v1/farms/leases/{leaseId:\\d+}")
	void deleteLease(@PathVariable Long leaseId);

}
