package com.infinite.solution.phoneshop.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ApiServiceException extends RuntimeException{
	private final HttpStatus status;
	private final String message;
}
