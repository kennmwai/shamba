/**
 * 
 */
package com.kenm.spring.farmclientservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kenm.spring.farmclientservice.dto.payload.request.FarmRequest;
import com.kenm.spring.farmclientservice.dto.payload.request.LeaseRequest;
import com.kenm.spring.farmclientservice.dto.payload.response.FarmDTO;
import com.kenm.spring.farmclientservice.dto.payload.response.FarmResponse;
import com.kenm.spring.farmclientservice.dto.payload.response.LeaseDTO;
import com.kenm.spring.farmclientservice.exception.ResourceNotFoundException;
import com.kenm.spring.farmclientservice.service.FarmClient;

import jakarta.validation.Valid;

/**
 * @author User
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/api/v1/farms")
public class FarmController {
	
	@Autowired
	private FarmClient farmClient;
	
	
	@GetMapping("/all")
	public ResponseEntity<List<FarmResponse>> getAllFarms() {
		List<FarmResponse> farmResources = farmClient.getAllFarms();
		return new ResponseEntity<>(farmResources, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<FarmResponse>> getFarmsByIds(@Valid @NonNull @RequestParam List<Long> ids)  {
		List<FarmResponse> farmDTOs = farmClient.getFarmsByIds(ids);
		return new ResponseEntity<>(farmDTOs, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<FarmResponse> getFarmById(@PathVariable @NonNull Long id)
			throws ResourceNotFoundException {
		FarmResponse farmData = farmClient.getFarmById(id);
		return new ResponseEntity<>(farmData, HttpStatus.OK);
	}

	@GetMapping("/price/{id}")
	public double calculateTotalPrice(@NonNull @PathVariable Long id) throws ResourceNotFoundException {
		double totalPrice = farmClient.calculateTotalPrice(id);
		return totalPrice;
	}

	@GetMapping("/exists/{id}")
	public boolean existsFarmById(@PathVariable @NonNull Long id) {
		boolean exists = farmClient.existsFarmById(id);
		return exists;
	}

	@GetMapping("/count")
	public long countFarms() {
		long count = farmClient.countFarms();
		return count;
	}

	@PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<FarmDTO> createFarm(@RequestBody @Valid @NonNull FarmRequest farmReqDTO) {
		FarmDTO createdFarmDTO = farmClient.createFarm(farmReqDTO);
		return new ResponseEntity<>(createdFarmDTO, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
	public ResponseEntity<FarmDTO> updateFarm(@PathVariable @NonNull Long id, @Valid @NonNull @RequestBody FarmRequest farmReqDTO)
			throws ResourceNotFoundException {
		FarmDTO updatedFarmDTO = farmClient.updateFarm(id, farmReqDTO);
		return new ResponseEntity<>(updatedFarmDTO, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteFarm(@PathVariable @NonNull Long id) {
		farmClient.deleteFarm(id);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteByIds(@Valid @NonNull @RequestParam List<Long> ids) {
		farmClient.deleteByIds(ids);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteAllFarms() {
		farmClient.deleteAllFarms();
		return ResponseEntity.noContent().build();
	}	

	
	// Lease
	
	@GetMapping("/{id}/leases")
	public ResponseEntity<List<LeaseDTO>> getLeaseByFarmId(@PathVariable Long id) {
		List<LeaseDTO> leaseDetails = farmClient.getLeaseByFarmId(id);
		return new ResponseEntity<>(leaseDetails, HttpStatus.OK);
	}

	@GetMapping("/leases/{id}")
	public ResponseEntity<?> getLeaseByLeasedId(@PathVariable Long id) {
		LeaseDTO leaseDetails = farmClient.getLeaseByLeasedId(id);
		return new ResponseEntity<>(leaseDetails, HttpStatus.OK);
	}

	@GetMapping("/leases/all")
	public ResponseEntity<?> getAllLeases() {
		List<LeaseDTO> leaseDetails = farmClient.getAllLeases();
		return new ResponseEntity<>(leaseDetails, HttpStatus.OK);
	}

	@GetMapping("/leases")
	public ResponseEntity<?> getLeaseByLeasedIds(@RequestParam List<Long> ids) {
		List<LeaseDTO> leaseDetails = farmClient.getLeaseByLeasedIds(ids);
		return new ResponseEntity<>(leaseDetails, HttpStatus.OK);
	}
	@PostMapping("/leases")
	@PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
	public ResponseEntity<LeaseDTO> createLease(@Valid @RequestBody LeaseRequest LeaseReqDTO) {
		LeaseDTO createdLeaseDTO = farmClient.createLease(LeaseReqDTO);
		return new ResponseEntity<>(createdLeaseDTO, HttpStatus.CREATED);
	}

	@PutMapping("/leases/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
	public ResponseEntity<LeaseDTO> updateLease(@PathVariable Long leaseId, @RequestBody LeaseRequest  LeaseReqDTO)
			throws ResourceNotFoundException {
		LeaseDTO updatedLeaseDTO = farmClient.updateLease(leaseId, LeaseReqDTO);
		return new ResponseEntity<>(updatedLeaseDTO, HttpStatus.OK);
	}

	@DeleteMapping("/leases/{leaseId:\\d+}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteLease(@PathVariable Long leaseId) {
		farmClient.deleteLease(leaseId);
		return ResponseEntity.noContent().build();
	}

}
