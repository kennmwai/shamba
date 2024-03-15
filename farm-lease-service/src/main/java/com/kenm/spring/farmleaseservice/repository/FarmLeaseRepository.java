package com.kenm.spring.farmleaseservice.repository;

import com.kenm.spring.farmleaseservice.FarmLease;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmLeaseRepository extends JpaRepository<FarmLease, Long> {

}
