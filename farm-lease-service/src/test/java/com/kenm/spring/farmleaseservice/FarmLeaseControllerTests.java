//package com.kenm.spring.farmleaseservice;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//import java.util.Arrays;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import com.kenm.spring.farmleaseservice.controller.FarmLeaseController;
//import com.kenm.spring.farmleaseservice.dto.FarmLeaseDTO;
//import com.kenm.spring.farmleaseservice.dto.payload.CreateLeaseRequest;
//import com.kenm.spring.farmleaseservice.dto.payload.UpdateLeaseRequest;
//import com.kenm.spring.farmleaseservice.service.FarmLeaseService;
//
//@WebMvcTest(FarmLeaseController.class)
//public class FarmLeaseControllerTests {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private FarmLeaseService farmLeaseService;
//
//    @Test
//    public void testGetAllFarmLeases() throws Exception {
//        // Mock the service response
//        List<FarmLeaseDTO> leases = Arrays.asList(new FarmLeaseDTO(), new FarmLeaseDTO());
//        when(farmLeaseService.findAll()).thenReturn(leases);
//
//        // Perform the GET request and verify the response
//        mockMvc.perform(get("/api/v1/leases/all"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$").isArray())
//                .andExpect(jsonPath("$.length()").value(2));
//    }
//
//    // Add more test cases for other methods
//
//    @Test
//    public void testGetFarmLeaseById() throws Exception {
//        // Mock the service response
//        FarmLeaseDTO lease = new FarmLeaseDTO();
//        when(farmLeaseService.findById(1L)).thenReturn(lease);
//
//        // Perform the GET request and verify the response
//        mockMvc.perform(get("/api/v1/leases/1"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").value(1L));
//    }
//
//    @Test
//    public void testCreateFarmLease() throws Exception {
//        CreateLeaseRequest request = new CreateLeaseRequest();
//        request.setLeaseTenant("John Doe");
//        FarmLeaseDTO createdLease = new FarmLeaseDTO();
//        createdLease.setId(1L);
//        when(farmLeaseService.createFarmLease(request)).thenReturn(createdLease);
//
//        mockMvc.perform(post("/api/v1/leases")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"tenant\":\"John Doe\"}"))
//                .andExpect(status().isCreated())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").value(1));
//    }
//
//    @Test
//    public void testUpdateFarmLease() throws Exception {
//        // Arrange
//        UpdateLeaseRequest request = new UpdateLeaseRequest();
//        request.setLeaseTenant("Jane Doe");
//        FarmLeaseDTO updatedLease = new FarmLeaseDTO();
//        updatedLease.setId(1L);
//        updatedLease.setLeaseTenant("Jane Doe");
//        when(farmLeaseService.updateFarmLease(1L, request)).thenReturn(updatedLease);
//
//        // Act & Assert
//        mockMvc.perform(put("/api/v1/leases/1")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"tenant\":\"Jane Doe\"}"))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(jsonPath("$.id").value(1))
//            .andExpect(jsonPath("$.tenant").value("Jane Doe"));
//    }
//
//    @Test
//    public void testDeleteFarmLease() throws Exception {
//        // Arrange
//        doNothing().when(farmLeaseService).delete(1L);
//
//        // Act & Assert
//        mockMvc.perform(delete("/api/v1/leases/1"))
//                .andExpect(status().isNoContent());
//    }
//    
//}
