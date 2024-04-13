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

import com.ken.spring.farmcommons.web.CreateFarmRequest;
import com.ken.spring.farmcommons.web.CreateLeaseRequest;
import com.ken.spring.farmcommons.web.GetFarmInfoResponse;
import com.ken.spring.farmcommons.web.GetLeaseInfoResponse;
import com.ken.spring.farmcommons.web.UpdateFarmRequest;
import com.ken.spring.farmcommons.web.UpdateLeaseRequest;

import jakarta.validation.Valid;

@FeignClient("farm-service")
public interface FarmClient {

	@GetMapping("/api/v1/farms/all")
	List<GetFarmInfoResponse> getAllFarms();

	@GetMapping("/api/v1/farms")
	List<GetFarmInfoResponse> getFarmsByIds(@RequestParam List<Long> farmIds);

	@GetMapping("/api/v1/farms/{farmId:\\d+}")
	GetFarmInfoResponse getFarmById(@PathVariable Long farmId);

	@GetMapping("/api/v1/farms/price/{farmId}")
	public double calculateTotalPrice(@PathVariable Long farmId);

	@GetMapping("/api/v1/farms/exists/{farmId}")
	public boolean existsFarmById(@PathVariable Long farmId);

	@GetMapping("/api/v1/farms/count")
	public long countFarms();

	@PostMapping("/api/v1/farms")
	GetFarmInfoResponse createFarm(@RequestBody CreateFarmRequest farm);

	@PutMapping("/api/v1/farms/{farmId}")
	GetFarmInfoResponse updateFarm(@PathVariable Long farmId, @RequestBody UpdateFarmRequest updateFarmRequest);

	@DeleteMapping("/api/v1/farms/{farmId}")
	void deleteFarm(@PathVariable Long farmId);

	@DeleteMapping("/api/v1/farms")
	void deleteByIds(@RequestParam List<Long> farmIds);

	@DeleteMapping("/api/v1/farms/all")
	void deleteAllFarms();

	// Lease

	@GetMapping("/api/v1/farms/{farmId}/leases")
	List<GetLeaseInfoResponse> getLeaseByFarmId(@PathVariable Long farmId);

	@GetMapping("/api/v1/farms/leases/{leaseId}")
	GetLeaseInfoResponse getLeaseByLeasedId(@PathVariable Long leaseId);

	@GetMapping("/api/v1/farms/leases/all")
	List<GetLeaseInfoResponse> getAllLeases();

	@GetMapping("/api/v1/farms/leases")
	List<GetLeaseInfoResponse> getLeaseByLeasedIds(@RequestParam List<Long> leaseIds);

	@PostMapping("/api/v1/farms/leases")
	GetLeaseInfoResponse createLease(@Valid @RequestBody CreateLeaseRequest createLeaseRequest);

	@PutMapping("/api/v1/farms/leases/{leaseId}")
	GetLeaseInfoResponse updateLease(@PathVariable Long leaseId, @RequestBody UpdateLeaseRequest updateLeaseRequest);

	@DeleteMapping("/api/v1/farms/leases/{leaseId:\\d+}")
	void deleteLease(@PathVariable Long leaseId);

}
