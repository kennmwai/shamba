package com.kenm.spring.farmservice;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenm.spring.farmservice.controller.FarmController;
import com.kenm.spring.farmservice.dto.FarmDTO;
import com.kenm.spring.farmservice.dto.payload.FarmResourceDTO;
import com.kenm.spring.farmservice.dto.payload.request.CreateFarmReq;
import com.kenm.spring.farmservice.service.FarmService;

@WebMvcTest(FarmController.class)
public class ControllerUnitTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private FarmService farmService;

	@Test
	public void should_return_all_farms() throws Exception {

		// Create a sample FarmDTO
		FarmDTO farmDTO = new FarmDTO();
		farmDTO.setId(1L);
		farmDTO.setFarmName("Test Farm");

		// Create a sample FarmResourceDTO containing the FarmDTO
		FarmResourceDTO farmResourceDTO = new FarmResourceDTO();
		farmResourceDTO.setFarm(farmDTO);

		// Mock the behavior of the farmService
		when(farmService.getAllFarms()).thenReturn(Collections.singletonList(farmResourceDTO));

		// Perform GET request and verify the response
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/farms/all").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].farm.farmName").value("Test Farm"));
	}

	@Test
	public void should_return_farms_by_ids() throws Exception {
		List<Long> ids = Arrays.asList(1L, 2L, 3L);
		// Mock the behavior of the farmService
		when(farmService.getFarmsByIds(ids)).thenReturn(Collections.emptyList());

		// Perform GET request and verify the response
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/farms").param("ids", "1", "2", "3")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void should_return_farm_by_id() throws Exception {
		Long id = 1L;
		// Mock the behavior of the farmService
		when(farmService.getFarmById(id)).thenReturn(new FarmResourceDTO());

		// Perform GET request and verify the response
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/farms/{id}", id).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void should_return_total_price_of_farm() throws Exception {
		Long id = 1L;
		double totalPrice = 1000.0; // Set your expected total price
		// Mock the behavior of the farmService
		when(farmService.calculateTotalPrice(id)).thenReturn(totalPrice);

		// Perform GET request and verify the response
		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/v1/farms/price/{id}", id).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(String.valueOf(totalPrice)));
	}

	@Test
	public void should_return_true_if_farm_exists_by_id() throws Exception {
		Long id = 1L;
		// Mock the behavior of the farmService
		when(farmService.exists(id)).thenReturn(true);

		// Perform GET request and verify the response
		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/v1/farms/exists/{id}", id).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("true"));
	}

	@Test
	public void should_create_new_farm() throws Exception {

		// Populate createFarmReq with necessary fields
		CreateFarmReq createFarmReq = CreateFarmReq.builder().farmName("Old Farm Name").farmOwner("Old Owner")
				.farmLocation("Old Location").farmType("Crop").farmStatus("Active").farmSize(100).pricePerAcre(10.0)
				.build();

		// Populate createdFarmDTO with necessary fields
		FarmDTO createdFarmDTO = FarmDTO.builder().farmName("Old Farm Name").farmOwner("Old Owner")
				.farmLocation("Old Location").farmType("Crop").farmStatus("Active").farmSize(100).pricePerAcre(10.0)
				.build();

		// Mock the behavior of the farmService
		when(farmService.createFarm(createFarmReq)).thenReturn(createdFarmDTO);

		// Perform POST request and verify the response
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/farms").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(createFarmReq)))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(createdFarmDTO.getId()));
	}

	@Test
	public void should_update_existing_farm() throws Exception {
		Long id = 1L;

		// Populate updateFarmDTO with updated fields
		FarmDTO updateFarmDTO = FarmDTO.builder().farmName("New Farm Name").farmOwner("New Owner")
				.farmLocation("New Location").farmType("Crop").farmStatus("Active").farmSize(100).pricePerAcre(10.0)
				.build();

		// Populate updatedFarmDTO with updated fields
		FarmDTO updatedFarmDTO = FarmDTO.builder().farmName("New Farm Name").farmOwner("New Owner")
				.farmLocation("New Location").farmType("Crop").farmStatus("Active").farmSize(100).pricePerAcre(10.0)
				.build();

		// Mock the behavior of the farmService
		when(farmService.updateFarm(id, updateFarmDTO)).thenReturn(updatedFarmDTO);

		// Perform PUT request and verify the response
		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/farms/{id}", id).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(updateFarmDTO)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(updatedFarmDTO.getId()));
	}

	@Test
	public void should_delete_existing_farm() throws Exception {
		Long id = 1L;

		// Perform DELETE request and verify the response
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/farms/{id}", id))
				.andExpect(MockMvcResultMatchers.status().isNoContent());
	}

}