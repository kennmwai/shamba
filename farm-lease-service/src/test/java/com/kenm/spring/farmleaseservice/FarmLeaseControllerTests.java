package com.kenm.spring.farmleaseservice;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenm.spring.farmleaseservice.controller.FarmLeaseController;
import com.kenm.spring.farmleaseservice.dto.FarmLeaseDTO;
import com.kenm.spring.farmleaseservice.dto.payload.CreateLeaseRequest;
import com.kenm.spring.farmleaseservice.dto.payload.UpdateLeaseRequest;
import com.kenm.spring.farmleaseservice.service.FarmLeaseService;

@WebMvcTest(FarmLeaseController.class)
public class FarmLeaseControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private FarmLeaseService leaseService;

	@Test
	public void testGetAll() throws Exception {
		// Mock the service response
		List<FarmLeaseDTO> leases = Arrays.asList(new FarmLeaseDTO(), new FarmLeaseDTO());

		when(leaseService.findAll()).thenReturn(leases);

		// Perform the GET request and verify the response
		mockMvc.perform(get("/api/v1/leases/all")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$.length()").value(2));
	}

	@Test
	public void testGetById() throws Exception {
		// Mock the service response
		FarmLeaseDTO lease = new FarmLeaseDTO();
		lease.setLeaseId(1L);

		when(leaseService.findById(1L)).thenReturn(lease);

		// Perform the GET request and verify the response
		mockMvc.perform(get("/api/v1/leases/1")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.leaseId").value(1L));
	}

	@Test
	public void testGetByIds() throws Exception {
		// Create a list of Ids
		List<Long> ids = Arrays.asList(1L, 2L, 3L);

		// Mock the behavior of the leaseService
		when(leaseService.findAllById(ids)).thenReturn(Collections.emptyList());

		// Perform GET request and verify the response
		mockMvc.perform(get("/api/v1/leases").param("ids", "1", "2", "3").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void testCreate() throws Exception {
		CreateLeaseRequest request = CreateLeaseRequest.builder().farmId(1L).leaseTenant("John Doe")
				.leaseType("short-term lease").leaseStatus("active").leaseRent(1000.0).leaseDuration("1 year")
				.leaseStart("2024-04-22").leaseEnd("2025-04-22").build();

		FarmLeaseDTO createdLease = new FarmLeaseDTO();
		createdLease.setLeaseId(1L);

		when(leaseService.createFarmLease(request)).thenReturn(createdLease);

		mockMvc.perform(post("/api/v1/leases").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request))).andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.leaseId").value(createdLease.getLeaseId()));
	}

	@Test
	public void testUpdate() throws Exception {
		// Arrange
		UpdateLeaseRequest request = new UpdateLeaseRequest();
		request.setLeaseTenant("John Doe");

		FarmLeaseDTO updatedLease = new FarmLeaseDTO();
		updatedLease.setLeaseId(1L);
		updatedLease.setLeaseTenant("Jane Doe");

		when(leaseService.updateFarmLease(1L, request)).thenReturn(updatedLease);

		// Act & Assert
		mockMvc.perform(put("/api/v1/leases/1").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request))).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.leaseId").value(1L))
				.andExpect(jsonPath("$.leaseTenant").value("Jane Doe"));
	}

	@Test
	public void testDeleteById() throws Exception {
		// Arrange
		doNothing().when(leaseService).delete(1L);

		// Act & Assert
		mockMvc.perform(delete("/api/v1/leases/1")).andExpect(status().isNoContent());
	}

}
