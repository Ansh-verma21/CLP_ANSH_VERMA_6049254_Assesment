package com.cg.Assessment3.exception;



public class DuplicateLoanApplicationException extends RuntimeException {
    public DuplicateLoanApplicationException(String message) {
        super(message);
    }
}