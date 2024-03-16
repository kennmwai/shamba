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

import com.kenm.spring.farmservice.dto.FarmDTO;
import com.kenm.spring.farmservice.exception.RecordNotFoundException;
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
    public List<FarmDTO> getAllFarms() {
        return farmService.findAll();
    }

    @GetMapping("/{id}")
    public FarmDTO getFarmById(@PathVariable Long id) throws RecordNotFoundException {
        FarmDTO farmDTO = farmService.findById(id);
        return farmDTO;
    }

    @PostMapping
    public ResponseEntity<FarmDTO> createFarm(@Valid @RequestBody FarmDTO farmDTO) {
        FarmDTO createdFarmDTO = farmService.createFarm(farmDTO);
        return new ResponseEntity<>(createdFarmDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public FarmDTO updateFarm(@PathVariable Long id, @RequestBody FarmDTO farmDTO)
            throws RecordNotFoundException {
        FarmDTO updatedFarmDTO = farmService.updateFarm(id, farmDTO);
        return updatedFarmDTO;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFarm(@PathVariable Long id) throws RecordNotFoundException {
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

    @GetMapping("/exists/{id}")
    public boolean existsFarmById(@PathVariable Long id) {
        boolean exists = farmService.existsById(id);
        return exists;
    }

    @GetMapping("/find-all-by-ids")
    public List<FarmDTO> getAllFarmsByIds(@RequestParam List<Long> ids) {
        List<FarmDTO> farmDTOs = farmService.findAllById(ids);
        return farmDTOs;
    }

    @DeleteMapping("/delete-all-by-ids")
    public ResponseEntity<?> deleteAllById(@RequestBody List<Long> ids) {
        farmService.deleteAllById(ids);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/price/{id}")
    public double calculateTotalPrice(@PathVariable Long id) throws RecordNotFoundException {
        double totalPrice = farmService.calculateTotalPrice(id);
        return totalPrice;
    }
}
