package com.kenm.spring.farmclientservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1", produces = "application/json")
public class ClientController {

    @RequestMapping
    public ResponseEntity<String> root() {
        return ResponseEntity.status(HttpStatus.MULTIPLE_CHOICES).body("This is the API root endpoint for the Farm Client Service.\nPlease refer to the API documentation for more information.");
    }

}
