package com.cg.Assessment3.exception;


import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidLoanAmountException.class)
    public ResponseEntity<ErrorResponse> handleInvalidAmount(InvalidLoanAmountException ex) {
        ErrorResponse err = new ErrorResponse("InvalidLoanAmountException", ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateLoanApplicationException.class)
    public ResponseEntity<ErrorResponse> handleDuplicate(DuplicateLoanApplicationException ex) {
        ErrorResponse err = new ErrorResponse("DuplicateLoanApplicationException", ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(err, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(LoanNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(LoanNotFoundException ex) {
        ErrorResponse err = new ErrorResponse("LoanNotFoundException", ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }
}
