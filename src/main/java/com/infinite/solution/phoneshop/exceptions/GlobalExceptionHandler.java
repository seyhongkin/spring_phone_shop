package com.infinite.solution.phoneshop.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler { 

	@ExceptionHandler(ApiServiceException.class)
	public ResponseEntity<?> handleApiException(ApiServiceException e){
		ErrorResponse errorResponse = new ErrorResponse(e.getStatus(), e.getMessage());
		return ResponseEntity
				.status(e.getStatus())
				.body(errorResponse);
	}
	
}

/*
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
  
	@ExceptionHandler(ApiServiceException.class)
	public ResponseEntity<?> handleException(ApiServiceException e){
		ErrorResponse responseBody = new ErrorResponse(e.getStatus(), e.getMessage());
		return ResponseEntity
				.status(e.getStatus())
				.body(responseBody);
	}

	
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {
    Map<String, List<String>> body = new HashMap<>();
    
    List<String> errors = ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(DefaultMessageSourceResolvable::getDefaultMessage)
        .collect(Collectors.toList());
    
    body.put("errors", errors);
    
    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
  }
}
*/