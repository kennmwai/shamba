package com.kenm.spring.farmservice.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kenm.spring.farmleaseservice.dto.FarmLeaseDTO;
@FeignClient(name = "farm-lease-service", url = "${farm-lease-service.url}")
public interface FarmLeaseServiceClient {
    @RequestMapping(method = RequestMethod.GET, value ="/api/leases")
    List<FarmLeaseDTO> getLeases();

    @RequestMapping(method = RequestMethod.GET, value ="/api/leases/{leaseId}")
    FarmLeaseDTO getLeaseById(@PathVariable Long leaseId);

    @RequestMapping(method = RequestMethod.POST, value ="/api/leases", consumes = "application/json")
    FarmLeaseDTO create(@RequestBody FarmLeaseDTO farmLeaseDTO);

    @PutMapping(path = "/api/leases/{leaseId}")
    FarmLeaseDTO update(@PathVariable Long leaseId, @RequestBody FarmLeaseDTO farmLeaseDTO);

    @RequestMapping(method = RequestMethod.DELETE, value ="/api/leases/{leaseId}")
    void delete(@PathVariable Long leaseId);

}
