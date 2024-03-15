/**
 * 
 */
package com.kenm.spring.farmleaseservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author User
 *
 */
@RestController
public class HomeController {
	
	@GetMapping("/")
    public String home() {
        return "Welcome to the Farm Lease Service!";
    }
	
}
