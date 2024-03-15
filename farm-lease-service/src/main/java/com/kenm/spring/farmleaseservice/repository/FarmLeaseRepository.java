package com.kenm.spring.farmleaseservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kenm.spring.farmleaseservice.FarmLease;

public interface FarmLeaseRepository extends JpaRepository<FarmLease, Long> {

}
