package com.infinite.solution.phoneshop.exceptions;

import org.springframework.http.HttpStatus;


public class ResourceNotFoundException extends ApiServiceException{

	public ResourceNotFoundException(String entityName, Long id) {
		super(HttpStatus.NOT_FOUND, String.format("%s_id = %d is not found", entityName,id));
	}

}
