package com.kenm.spring.farmservice.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kenm.spring.farmservice.entity.CustomErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class CustomErrorController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @RequestMapping(value = ERROR_PATH)
    public ResponseEntity<CustomErrorResponse> handleError(HttpServletRequest request) {
        // Map exception to custom error response
        HttpStatus status = getStatus(request);
        
        CustomErrorResponse errorResponse = new CustomErrorResponse();
        errorResponse.setStatus(status.value());
        errorResponse.setError(status.getReasonPhrase());
        errorResponse.setMessage("Farm Lease with id not found."); // Custom message
        errorResponse.setPath((String) request.getAttribute("jakarta.servlet.error.request_uri"));

        return new ResponseEntity<>(errorResponse, status);
    }
    public String getErrorPath() {
        return ERROR_PATH;
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("jakarta.servlet.error.status_code");
        return HttpStatus.valueOf(statusCode);
    }
}
