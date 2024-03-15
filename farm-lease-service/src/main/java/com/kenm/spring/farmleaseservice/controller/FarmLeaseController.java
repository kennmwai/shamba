/**
 * 
 */
package com.kenm.spring.farmleaseservice.controller;


import com.kenm.spring.farmleaseservice.FarmLease;
import com.kenm.spring.farmleaseservice.service.FarmLeaseService;
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

import java.util.List;


/**
 * @author User
 */
@RestController
@RequestMapping(path="/api/leases", produces = "application/json")
public class FarmLeaseController {

    @Autowired
    private FarmLeaseService farmLeaseService;

    @GetMapping
    public ResponseEntity<List<FarmLease>> getAllFarmLeases() {
        List<FarmLease> farmLeases = farmLeaseService.findAll();
        return new ResponseEntity<>(farmLeases, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FarmLease> findById(@PathVariable Long id) {
        FarmLease farmLease = farmLeaseService.findById(id);
        if (farmLease == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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

    @GetMapping("/{ids}")
    public ResponseEntity<List<FarmLease>> findAllById(@RequestBody List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<FarmLease> farmLeases = farmLeaseService.findAllById(ids);
        return new ResponseEntity<>(farmLeases, HttpStatus.OK);
    }

    @DeleteMapping("/{ids}")
	public ResponseEntity<Void> deleteAllById(@RequestBody List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        farmLeaseService.deleteAllById(ids);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<Void> deleteAll(@RequestBody List<FarmLease> entities) {
        if (entities == null || entities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        farmLeaseService.deleteAll(entities);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
 }