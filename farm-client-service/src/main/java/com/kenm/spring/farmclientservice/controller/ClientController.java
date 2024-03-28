package com.kenm.spring.farmclientservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ClientController {

    @RequestMapping
    public ResponseEntity<String> root() {
        return ResponseEntity.status(HttpStatus.OK).body("Welcome to Shamba the Rent a Farm Service!");
    }
    

}
