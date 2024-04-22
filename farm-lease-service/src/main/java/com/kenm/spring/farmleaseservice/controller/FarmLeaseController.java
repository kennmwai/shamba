/**
 * FarmLeaseController.java
 */
package com.kenm.spring.farmleaseservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kenm.spring.farmleaseservice.dto.FarmLeaseDTO;
import com.kenm.spring.farmleaseservice.dto.payload.CreateLeaseRequest;
import com.kenm.spring.farmleaseservice.dto.payload.UpdateLeaseRequest;
import com.kenm.spring.farmleaseservice.exception.ResourceNotFoundException;
import com.kenm.spring.farmleaseservice.service.FarmLeaseService;

import jakarta.validation.Valid;

/**
 * @author User
 */
@RestController
@RequestMapping(path = "/api/v1/leases", produces = "application/json")
public class FarmLeaseController {

	@Autowired
	private FarmLeaseService farmLeaseService;

	@GetMapping("/exists/{id}")
	public boolean existsFarmLeaseById(@PathVariable @NonNull Long id) {
		boolean exists = farmLeaseService.exists(id);
		return exists;
	}

	@GetMapping("/count")
	public long countFarmLeases() {
		long count = farmLeaseService.count();
		return count;
	}

	@GetMapping("/all")
	public ResponseEntity<List<FarmLeaseDTO>> getAllFarmLeases() {
		return new ResponseEntity<>(farmLeaseService.findAll(), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<FarmLeaseDTO>> getAllFarmLeasesByIds(@RequestParam @NonNull List<Long> ids)
			throws ResourceNotFoundException {
		List<FarmLeaseDTO> farmLeaseDTOs = farmLeaseService.findAllById(ids);
		return new ResponseEntity<>(farmLeaseDTOs, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<FarmLeaseDTO> getFarmLeaseById(@PathVariable @NonNull Long id) throws ResourceNotFoundException {
		FarmLeaseDTO farmLeaseDTO = farmLeaseService.findById(id);
		return new ResponseEntity<>(farmLeaseDTO, HttpStatus.OK);
	}

	@GetMapping("/farms/{id}")
	public ResponseEntity<?> getFarmLeaseByFarmId(@PathVariable @NonNull Long id) throws ResourceNotFoundException {
		List<FarmLeaseDTO> farmLeaseDTO = farmLeaseService.getFarmLeaseByFarmId(id);
		return new ResponseEntity<>(farmLeaseDTO, HttpStatus.OK);
	}

	@GetMapping("/status")
	public ResponseEntity<List<FarmLeaseDTO>> getFarmLeaseByStatus(@RequestParam String status)
			throws ResourceNotFoundException {
		List<FarmLeaseDTO> farmLeaseDTOs = farmLeaseService.getFarmLeaseByStatus(status);
		return new ResponseEntity<>(farmLeaseDTOs, HttpStatus.OK);
	}

	@GetMapping("/tenant")
	public ResponseEntity<List<FarmLeaseDTO>> getFarmLeaseByTenant(@RequestParam String tenant)
			throws ResourceNotFoundException {
		List<FarmLeaseDTO> farmLeaseDTOs = farmLeaseService.getFarmLeaseByTenant(tenant);
		return new ResponseEntity<>(farmLeaseDTOs, HttpStatus.OK);
	}

	@GetMapping("/type")
	public ResponseEntity<List<FarmLeaseDTO>> getFarmLeaseByType(@RequestParam String type)
			throws ResourceNotFoundException {
		List<FarmLeaseDTO> farmLeaseDTOs = farmLeaseService.getFarmLeaseByType(type);
		return new ResponseEntity<>(farmLeaseDTOs, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<FarmLeaseDTO> createFarmLease(@NonNull @Valid @RequestBody CreateLeaseRequest createLeaseDTO) {
		FarmLeaseDTO createdFarmLeaseDTO = farmLeaseService.createFarmLease(createLeaseDTO);
		return new ResponseEntity<>(createdFarmLeaseDTO, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<FarmLeaseDTO> updateFarmLease(@PathVariable @NonNull Long id, @Valid @RequestBody UpdateLeaseRequest updateLeaseDTO)
			throws ResourceNotFoundException {
		FarmLeaseDTO updatedFarmLeaseDTO = farmLeaseService.updateFarmLease(id, updateLeaseDTO);
		return new ResponseEntity<>(updatedFarmLeaseDTO, HttpStatus.OK);
	}

	@DeleteMapping("/all")
	public ResponseEntity<?> deleteAllFarmLeases() throws ResourceNotFoundException {
		farmLeaseService.deleteAll();
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping
	public ResponseEntity<?> deleteByIds(@RequestParam @NonNull List<Long> ids) throws ResourceNotFoundException {
		farmLeaseService.deleteByIds(ids);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteFarmLease(@PathVariable @NonNull Long id) throws ResourceNotFoundException {
		farmLeaseService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
