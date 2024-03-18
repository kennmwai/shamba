package com.kenm.spring.farmservice.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

import com.kenm.spring.farmleaseservice.dto.FarmLeaseDTO;
import com.kenm.spring.farmservice.config.FeignClientConfig;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

@FeignClient(name = "farm-lease-service", url = "${farm-lease-service.url}", configuration = FeignClientConfig.class)
public interface FarmLeaseServiceClient {
    @RequestLine("GET /api/leases")
    List<FarmLeaseDTO> getFarmLeases();

    @RequestLine("GET /api/leases/{id}")
    FarmLeaseDTO getFarmLeaseById(@Param("id") Long id);

    @Headers("Content-Type: application/json")
    @RequestLine("POST /api/leases")
    FarmLeaseDTO createFarmLease(@RequestBody FarmLeaseDTO farmLeaseDTO);

    @RequestLine("PUT /api/leases/{id}")
    FarmLeaseDTO updateFarmLease(@Param("id") Long id, @RequestBody FarmLeaseDTO farmLeaseDTO);

    @RequestLine("DELETE /api/leases/{id}")
    void deleteFarmLease(@Param("id") Long id);

}
