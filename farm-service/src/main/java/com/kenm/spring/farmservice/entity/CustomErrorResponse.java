package com.kenm.spring.farmservice.entity;

import lombok.Data;

@Data
public class CustomErrorResponse {

	private int status;
	private String error;
	private String message;
	private String path;
}
