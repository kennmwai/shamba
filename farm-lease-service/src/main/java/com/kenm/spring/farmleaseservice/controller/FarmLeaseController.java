/**
 * FarmLeaseController.java
 */
package com.kenm.spring.farmleaseservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.kenm.spring.farmleaseservice.entity.FarmPayment;
import com.kenm.spring.farmleaseservice.exception.ResourceNotFoundException;
import com.kenm.spring.farmleaseservice.repository.FarmPaymentRepository;
import com.kenm.spring.farmleaseservice.service.FarmLeaseService;

import jakarta.validation.Valid;

/**
 * @author User
 */
@RestController
@RequestMapping(path = "/api/leases", produces = "application/json")
public class FarmLeaseController {
	@Autowired
	private FarmLeaseService farmLeaseService;
	
	@Autowired
	private FarmPaymentRepository farmPaymentRepository;

	@GetMapping
	public ResponseEntity<List<FarmLeaseDTO>> getAllFarmLeases() {
		return new ResponseEntity<>(farmLeaseService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<FarmLeaseDTO> getFarmLeaseById(@PathVariable Long id) throws ResourceNotFoundException {
		FarmLeaseDTO farmLeaseDTO = farmLeaseService.findById(id);
		return new ResponseEntity<>(farmLeaseDTO, HttpStatus.OK);
	}

	@GetMapping("/farms/{id}")
	public ResponseEntity<FarmLeaseDTO> getFarmLeaseByFarmId(@PathVariable Long id) throws ResourceNotFoundException {
		FarmLeaseDTO farmLeaseDTO = farmLeaseService.findByFarmId(id);
		return new ResponseEntity<>(farmLeaseDTO, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<FarmLeaseDTO> createFarmLease(@Valid @RequestBody FarmLeaseDTO farmLeaseDTO) {
		FarmLeaseDTO createdFarmLeaseDTO = farmLeaseService.createFarmLease(farmLeaseDTO);
		return new ResponseEntity<>(createdFarmLeaseDTO, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<FarmLeaseDTO> updateFarmLease(@PathVariable Long id, @RequestBody FarmLeaseDTO farmLeaseDTO)
			throws ResourceNotFoundException {
		FarmLeaseDTO updatedFarmLeaseDTO = farmLeaseService.updateFarmLease(id, farmLeaseDTO);
		return new ResponseEntity<>(updatedFarmLeaseDTO, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteFarmLease(@PathVariable Long id) throws ResourceNotFoundException {
		farmLeaseService.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping
	public ResponseEntity<?> deleteAllFarmLeases() {
		farmLeaseService.deleteAll();
		return ResponseEntity.ok().build();
	}

	@GetMapping("/count")
	public long countFarmLeases() {
		long count = farmLeaseService.count();
		return count;
	}

	@GetMapping("/exists/{id}")
	public boolean existsFarmLeaseById(@PathVariable Long id) {
		boolean exists = farmLeaseService.existsById(id);
		return exists;
	}

	@GetMapping("/find-all-by-ids")
	public ResponseEntity<List<FarmLeaseDTO>> getAllFarmLeasesByIds(@RequestParam List<Long> ids)
			throws ResourceNotFoundException {
		List<FarmLeaseDTO> farmLeaseDTOs = farmLeaseService.findAllById(ids);
		return new ResponseEntity<>(farmLeaseDTOs, HttpStatus.OK);
	}

	@DeleteMapping("/delete-all-by-ids")
	public ResponseEntity<?> deleteAllById(@RequestBody List<Long> ids) throws ResourceNotFoundException {
		farmLeaseService.deleteAllById(ids);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/status/{status}")
	public ResponseEntity<List<FarmLeaseDTO>> getFarmLeaseByStatus(@PathVariable String status)
			throws ResourceNotFoundException {
		List<FarmLeaseDTO> farmLeaseDTOs = farmLeaseService.getFarmLeaseByStatus(status);
		return new ResponseEntity<>(farmLeaseDTOs, HttpStatus.OK);
	}

	@GetMapping("/type/{type}")
	public ResponseEntity<List<FarmLeaseDTO>> getFarmLeaseByType(@PathVariable String type)
			throws ResourceNotFoundException {
		List<FarmLeaseDTO> farmLeaseDTOs = farmLeaseService.getFarmLeaseByType(type);
		return new ResponseEntity<>(farmLeaseDTOs, HttpStatus.OK);
	}

	@GetMapping("/tenant/{tenant}")
	public ResponseEntity<List<FarmLeaseDTO>> getFarmLeaseByTenant(@PathVariable String tenant)
			throws ResourceNotFoundException {
		List<FarmLeaseDTO> farmLeaseDTOs = farmLeaseService.getFarmLeaseByTenant(tenant);
		return new ResponseEntity<>(farmLeaseDTOs, HttpStatus.OK);
	}

	@GetMapping("/payments")
	public ResponseEntity<List<FarmPayment>> getLeasePayments() {
		List<FarmPayment> payments = farmPaymentRepository.findAll();
		return new ResponseEntity<>(payments, HttpStatus.OK);
	}
}
