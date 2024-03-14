/**
 * 
 */
package com.kenm.spring.farmleaseservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kenm.spring.farmleaseservice.FarmLease;
import com.kenm.spring.farmleaseservice.service.FarmLeaseService;

/**
 * @author User
 *
 */
@RestController
@RequestMapping("/api/farm-leases")
public class FarmLeaseController {

 @Autowired
 private FarmLeaseService farmLeaseService;

 @GetMapping
 public List<FarmLease> findAll() {
     return farmLeaseService.findAll();
 }

 @GetMapping("/{id}")
 public FarmLease findById(@PathVariable Long id) {
     return farmLeaseService.findById(id);
 }

 @PostMapping
 public FarmLease save(@RequestBody FarmLease farmLease) {
     return farmLeaseService.save(farmLease);
 }

 @PutMapping("/{id}")
 public FarmLease update(@PathVariable Long id, @RequestBody FarmLease farmLease) {
     FarmLease existingFarmLease = farmLeaseService.findById(id);
     // Update the existing farm lease with the new data
     return farmLeaseService.save(existingFarmLease);
 }

 @DeleteMapping("/{id}")
 public void deleteById(@PathVariable Long id) {
     farmLeaseService.deleteById(id);
 }
}