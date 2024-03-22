package com.kenm.spring.farmleaseservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kenm.spring.farmleaseservice.entity.FarmLease;

public interface FarmLeaseRepository extends JpaRepository<FarmLease, Long> {

    @Query("SELECT f FROM FarmLease f WHERE f.farmId = ?1")
    Optional<FarmLease> findByFarmId(Long id);

}
