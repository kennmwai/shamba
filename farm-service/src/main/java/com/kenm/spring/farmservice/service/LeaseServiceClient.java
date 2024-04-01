package com.kenm.spring.farmservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import com.kenm.spring.farmleaseservice.dto.FarmLeaseDTO;
import com.kenm.spring.farmservice.exception.ResourceNotFoundException;

@FeignClient(name = "farm-lease-service", url = "${farm-lease-service.url}")
public interface LeaseServiceClient {
    @GetMapping("/api/leases")
    List<FarmLeaseDTO> getLeases();

    @GetMapping("/api/leases/{leaseId}")
    Optional <FarmLeaseDTO> getLeaseById(@PathVariable Long leaseId) throws ResourceNotFoundException;

    @GetMapping("/api/leases/farms/{farmId}")
    FarmLeaseDTO getLeaseByFarmId(@PathVariable Long farmId);

    @PostMapping(value = "/api/leases", consumes = "application/json")
    FarmLeaseDTO create(@RequestBody FarmLeaseDTO farmLeaseDTO);

    @PutMapping(path = "/api/leases/{leaseId}")
    FarmLeaseDTO update(@PathVariable Long leaseId, @RequestBody FarmLeaseDTO farmLeaseDTO);

    @DeleteMapping("/api/leases/{leaseId}")
    void delete(@PathVariable Long leaseId);

}
