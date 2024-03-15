/**
 *
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
import org.springframework.web.bind.annotation.RestController;

import com.kenm.spring.farmleaseservice.FarmLease;
import com.kenm.spring.farmleaseservice.exception.RecordNotFoundException;
import com.kenm.spring.farmleaseservice.service.FarmLeaseService;

/**
 * @author User
 */
@RestController
@RequestMapping(path = "/api/leases", produces = "application/json")
public class FarmLeaseController {

	@Autowired
	private FarmLeaseService farmLeaseService;

	@GetMapping
	public ResponseEntity<List<FarmLease>> getAllFarmLeases() {
		List<FarmLease> farmLeases = farmLeaseService.findAll();
		return new ResponseEntity<>(farmLeases, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<FarmLease> findById(@PathVariable Long id) throws RecordNotFoundException {
		FarmLease farmLease = farmLeaseService.findById(id);
		if (farmLease == null) {
			throw new RecordNotFoundException("Farm Lease with id " + id + " not found.");
		}
		return new ResponseEntity<>(farmLease, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<FarmLease> createFarmLease(@RequestBody FarmLease farmLease) {
		FarmLease createdFarmLease = farmLeaseService.createFarmLease(farmLease);
		return new ResponseEntity<>(createdFarmLease, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<FarmLease> updateFarmLease(@PathVariable Long id, @RequestBody FarmLease farmLease) {
		FarmLease existingFarmLease = farmLeaseService.findById(id);
		if (existingFarmLease == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		farmLease.setId(id);
		FarmLease updatedFarmLease = farmLeaseService.updateFarmLease(farmLease);
		return new ResponseEntity<>(updatedFarmLease, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteFarmLease(@PathVariable Long id) {
		FarmLease farmLease = farmLeaseService.findById(id);
		if (farmLease == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		farmLeaseService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping
	public ResponseEntity<Void> deleteAllFarmLeases() {
		farmLeaseService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/exists/{id}")
	public ResponseEntity<Boolean> existsFarmLeaseById(@PathVariable Long id) {
		boolean exists = farmLeaseService.existsById(id);
		return new ResponseEntity<>(exists, HttpStatus.OK);
	}

	@GetMapping("/count")
	public ResponseEntity<Long> countFarmLeases() {
		long count = farmLeaseService.count();
		return new ResponseEntity<>(count, HttpStatus.OK);
	}
}