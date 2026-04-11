package com.example.orderService.exception;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleUser(UserNotFoundException ex) {
		
		ErrorResponse err = new ErrorResponse("UserNotFoundException", ex.getMessage());
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleProduct(ProductNotFoundException ex) {
		
		ErrorResponse err = new ErrorResponse("ProductNotFoundException", ex.getMessage());
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
		
		Map<String, String> errors = new HashMap<>();
		for (FieldError error : ex.getFieldErrors()) {
			errors.put(error.getField(), error.getDefaultMessage());
		}
		
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
}
