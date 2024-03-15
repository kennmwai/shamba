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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kenm.spring.farmleaseservice.dto.FarmLeaseDTO;
import com.kenm.spring.farmleaseservice.exception.RecordNotFoundException;
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

    @GetMapping
    public List<FarmLeaseDTO> getAllFarmLeases() {
        return farmLeaseService.findAll();
    }
    
    @GetMapping("/{id}")
    public FarmLeaseDTO getFarmLeaseById(@PathVariable Long id) throws RecordNotFoundException {
        FarmLeaseDTO farmLeaseDTO = farmLeaseService.findById(id);
        return farmLeaseDTO;
    }

    @PostMapping
    public ResponseEntity<FarmLeaseDTO> createFarmLease(@Valid @RequestBody FarmLeaseDTO farmLeaseDTO) {
        FarmLeaseDTO createdFarmLeaseDTO = farmLeaseService.createFarmLease(farmLeaseDTO);
        return new ResponseEntity<>(createdFarmLeaseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public FarmLeaseDTO updateFarmLease(@PathVariable Long id, @RequestBody FarmLeaseDTO farmLeaseDTO)
            throws RecordNotFoundException {
        FarmLeaseDTO updatedFarmLeaseDTO = farmLeaseService.updateFarmLease(id, farmLeaseDTO);
        return updatedFarmLeaseDTO;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFarmLease(@PathVariable Long id) throws RecordNotFoundException {
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
    public List<FarmLeaseDTO> getAllFarmLeasesByIds(@RequestParam List<Long> ids) {
        List<FarmLeaseDTO> farmLeaseDTOs = farmLeaseService.findAllById(ids);
        return farmLeaseDTOs;
    }

    @DeleteMapping("/delete-all-by-ids")
    public ResponseEntity<?> deleteAllById(@RequestBody List<Long> ids) {
        farmLeaseService.deleteAllById(ids);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/price/{id}")
    public double calculateTotalPrice(@PathVariable Long id) throws RecordNotFoundException {
        double totalPrice = farmLeaseService.calculateTotalPrice(id);
        return totalPrice;
    }
}
