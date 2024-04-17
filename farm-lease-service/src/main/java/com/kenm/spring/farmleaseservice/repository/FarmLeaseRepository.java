package com.kenm.spring.farmleaseservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kenm.spring.farmleaseservice.entity.FarmLease;

public interface FarmLeaseRepository extends JpaRepository<FarmLease, Long> {

	@Query("SELECT f FROM FarmLease f WHERE f.farmId = ?1")
	Optional<List<FarmLease>> findByFarmId(Long id);

	@Query("SELECT f FROM FarmLease f WHERE f.leaseStatus = ?1")
	Optional<List<FarmLease>> findByStatus(String status);

	@Query("SELECT f FROM FarmLease f WHERE f.leaseTenant = ?1")
	Optional<List<FarmLease>> findByTenant(String tenant);

	@Query("SELECT f FROM FarmLease f WHERE f.leaseType = ?1")
	Optional<List<FarmLease>> findByType(String type);

}
