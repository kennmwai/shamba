/**
 * FarmController.java
 */
package com.kenm.spring.farmservice.controller;

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

import com.kenm.spring.farmservice.dto.FarmDTO;
import com.kenm.spring.farmservice.dto.payload.FarmResourceDTO;
import com.kenm.spring.farmservice.dto.payload.request.CreateFarmReq;
import com.kenm.spring.farmservice.exception.ResourceNotFoundException;
import com.kenm.spring.farmservice.service.FarmService;

import jakarta.validation.Valid;

/**
 * @author User
 */
@RestController
@RequestMapping(path = "/api/v1/farms", produces = "application/json")
public class FarmController {
	@Autowired
	private FarmService farmService;

	// @GetMapping
	// public ResponseEntity<Page<FarmDetails>> getAllFarms(@RequestParam(value =
	// "page", defaultValue = "0") int page,
	// @RequestParam(value = "size", defaultValue = "10") int size) {
	// Pageable pageable = PageRequest.of(page, size);
	// Page<FarmDetails> farmDTOs = farmService.findAll(pageable);
	// return ResponseEntity.ok(farmDTOs);
	// }

	@GetMapping("/all")
	public ResponseEntity<List<FarmResourceDTO>> getAllFarms() {
		List<FarmResourceDTO> farmResources = farmService.getAllFarms();
		return new ResponseEntity<>(farmResources, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<FarmResourceDTO>> getFarmsByIds(@Valid @NonNull @RequestParam List<Long> ids) throws ResourceNotFoundException {
		List<FarmResourceDTO> farmDTOs = farmService.getFarmsByIds(ids);
		return new ResponseEntity<>(farmDTOs, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<FarmResourceDTO> getFarmById(@PathVariable @NonNull Long id)
			throws ResourceNotFoundException {
		FarmResourceDTO farmData = farmService.getFarmById(id);
		return new ResponseEntity<>(farmData, HttpStatus.OK);
	}

	@GetMapping("/price/{id}")
	public double calculateTotalPrice(@NonNull @PathVariable Long id) throws ResourceNotFoundException {
		double totalPrice = farmService.calculateTotalPrice(id);
		return totalPrice;
	}

	@GetMapping("/exists/{id}")
	public boolean existsFarmById(@PathVariable @NonNull Long id) {
		boolean exists = farmService.exists(id);
		return exists;
	}

	@GetMapping("/count")
	public long countFarms() {
		long count = farmService.count();
		return count;
	}

	@PostMapping
	public ResponseEntity<FarmDTO> createFarm(@RequestBody @Valid @NonNull CreateFarmReq farmDTO) {
		FarmDTO createdFarmDTO = farmService.createFarm(farmDTO);
		return new ResponseEntity<>(createdFarmDTO, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<FarmDTO> updateFarm(@PathVariable @NonNull Long id, @Valid @NonNull @RequestBody FarmDTO farmDTO)
			throws ResourceNotFoundException {
		FarmDTO updatedFarmDTO = farmService.updateFarm(id, farmDTO);
		return new ResponseEntity<>(updatedFarmDTO, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteFarm(@PathVariable @NonNull Long id) throws ResourceNotFoundException {
		farmService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping
	public ResponseEntity<?> deleteByIds(@RequestParam @NonNull List<Long> ids) {
		farmService.deleteAllById(ids);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/all")
	public ResponseEntity<?> deleteAllFarms() {
		farmService.deleteAll();
		return ResponseEntity.noContent().build();
	}

}
