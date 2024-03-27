/**
 * FarmController.java
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

import com.kenm.spring.farmservice.dto.FarmDetailsDTO;
import com.kenm.spring.farmservice.dto.FarmResourceDTO;
import com.kenm.spring.farmservice.exception.ResourceNotFoundException;
import com.kenm.spring.farmservice.service.FarmService;

import jakarta.validation.Valid;

/**
 * @author User
 */
@RestController
@RequestMapping(path = "/api/farms", produces = "application/json")
public class FarmController {
    @Autowired
    private FarmService farmService;


    @GetMapping
    public ResponseEntity<List<FarmResourceDTO>> getAllFarms() {
        List<FarmResourceDTO> farmResources = farmService.getAllFarms();
        return new ResponseEntity<>(farmResources, HttpStatus.OK);
    }

    // @GetMapping
    // public ResponseEntity<Page<FarmDetails>> getAllFarms(@RequestParam(value = "page", defaultValue = "0") int page,
    //                                                 @RequestParam(value = "size", defaultValue = "10") int size) {
    //     Pageable pageable = PageRequest.of(page, size);
    //     Page<FarmDetails> farmDTOs = farmService.findAll(pageable);
    //     return ResponseEntity.ok(farmDTOs);
    // }

    @GetMapping("/{id}")
    public ResponseEntity<FarmDetailsDTO> getFarmById(@PathVariable Long id) throws ResourceNotFoundException {
        FarmDetailsDTO farmDTO = farmService.findById(id);
        return new ResponseEntity<>(farmDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FarmDetailsDTO> createFarm(@Valid @RequestBody FarmDetailsDTO farmDTO) {
        FarmDetailsDTO createdFarmDTO = farmService.createFarm(farmDTO);
        return new ResponseEntity<>(createdFarmDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FarmDetailsDTO> updateFarm(@PathVariable Long id, @RequestBody FarmDetailsDTO farmDTO)
            throws ResourceNotFoundException {
        FarmDetailsDTO updatedFarmDTO = farmService.updateFarm(id, farmDTO);
        return new ResponseEntity<>(updatedFarmDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFarm(@PathVariable Long id) throws ResourceNotFoundException {
        farmService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAllFarms() {
        farmService.deleteAll();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/count")
    public long countFarms() {
        long count = farmService.count();
        return count;
    }

    @GetMapping("/price/{id}")
    public double calculateTotalPrice(@PathVariable Long id) throws ResourceNotFoundException {
        double totalPrice = farmService.calculateTotalPrice(id);
        return totalPrice;
    }

    @GetMapping("/exists/{id}")
    public boolean existsFarmById(@PathVariable Long id) {
        boolean exists = farmService.existsById(id);
        return exists;
    }

    @GetMapping("/find-all-by-ids")
    public ResponseEntity<List<FarmDetailsDTO>> getAllFarmsByIds(@RequestParam List<Long> ids) {
        List<FarmDetailsDTO> farmDTOs = farmService.findAllById(ids);
        return new ResponseEntity<>(farmDTOs, HttpStatus.OK);
    }

    @DeleteMapping("/delete-all-by-ids")
    public ResponseEntity<?> deleteAllById(@RequestBody List<Long> ids) {
        farmService.deleteAllById(ids);
        return ResponseEntity.ok().build();
    }

    // Leases
    // @GetMapping("/leases")
    // public ResponseEntity<Page<FarmDTO>> getAllFarmsLeases(@RequestParam(value = "page", defaultValue = "0") int page,
    //                                                 @RequestParam(value = "size", defaultValue = "10") int size) {
    //     Pageable pageable = PageRequest.of(page, size);
    //     Page<FarmDTO> farmDTOs = farmService.findAllLeases(pageable);
    //     return ResponseEntity.ok(farmDTOs);

}
