package com.kenm.spring.farmleaseservice.entity;

import lombok.Data;

@Data
public class CustomErrorResponse {

    private int status;
    private String error;
    private String message;
    private String path;
}
