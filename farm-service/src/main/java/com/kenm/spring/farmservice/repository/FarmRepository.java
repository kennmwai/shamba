package com.kenm.spring.farmservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kenm.spring.farmservice.entity.Farm;

public interface FarmRepository extends JpaRepository<Farm, Long> {

}
