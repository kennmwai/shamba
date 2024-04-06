/**
 *
 */
package com.kenm.spring.farmservice.controller;

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

import com.kenm.spring.farmservice.dto.payload.request.LeaseReqDTO;
import com.kenm.spring.farmservice.dto.payload.response.FarmLeaseDTO;
import com.kenm.spring.farmservice.exception.ResourceNotFoundException;
import com.kenm.spring.farmservice.service.LeaseServiceClient;

import jakarta.validation.Valid;

/**
 * @author User
 *
 */
@RestController
@RequestMapping(path = "/api/v1/farms", produces = "application/json")
public class LeasesController {

	@Autowired
	private LeaseServiceClient leaseServiceClient;

	@GetMapping("/{id}/leases")
	public ResponseEntity<?> getLeaseByFarmId(@PathVariable Long id) {
		List<FarmLeaseDTO> leaseDetails = leaseServiceClient.getLeaseByFarmId(id);
		return new ResponseEntity<>(leaseDetails, HttpStatus.OK);
	}

	@GetMapping("/leases/{id}")
	public ResponseEntity<?> getLeaseByLeasedId(@PathVariable Long id) {
		FarmLeaseDTO leaseDetails = leaseServiceClient.getLeaseById(id);
		return new ResponseEntity<>(leaseDetails, HttpStatus.OK);
	}

	@GetMapping("/leases/all")
	public ResponseEntity<?> getAllLeases() {
		List<FarmLeaseDTO> leaseDetails = leaseServiceClient.getLeases();
		return new ResponseEntity<>(leaseDetails, HttpStatus.OK);
	}

	@GetMapping("/leases")
	public ResponseEntity<?> getLeaseByLeasedIds(@RequestParam List<Long> ids) {
		List<FarmLeaseDTO> leaseDetails = leaseServiceClient.getLeaseByLeasedId(ids);
		return new ResponseEntity<>(leaseDetails, HttpStatus.OK);
	}
	@PostMapping("/leases")
	public ResponseEntity<FarmLeaseDTO> createLease(@Valid @RequestBody LeaseReqDTO LeaseDTO) {
		FarmLeaseDTO createdLeaseDTO = leaseServiceClient.create(LeaseDTO);
		return new ResponseEntity<>(createdLeaseDTO, HttpStatus.CREATED);
	}

	@PutMapping("/leases/{id}")
	public ResponseEntity<FarmLeaseDTO> updateLease(@PathVariable Long id, @RequestBody FarmLeaseDTO farmLeaseDTO)
			throws ResourceNotFoundException {
		FarmLeaseDTO updatedLeaseDTO = leaseServiceClient.update(id, farmLeaseDTO);
		return new ResponseEntity<>(updatedLeaseDTO, HttpStatus.OK);
	}

	@DeleteMapping("/leases/{leaseId:\\d+}")
	public ResponseEntity<?> deleteLease(@PathVariable Long leaseId) {
		leaseServiceClient.delete(leaseId);
		return ResponseEntity.noContent().build();
	}

}
