package com.kenm.spring.farmclientservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public ResponseEntity<String> root() {
        return ResponseEntity.status(HttpStatus.MULTIPLE_CHOICES).body("/api/v1/* - API Endpoints");
    }

}
