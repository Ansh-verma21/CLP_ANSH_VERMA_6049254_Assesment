package com.cg.Assessment3.exception;



public class InvalidLoanAmountException extends RuntimeException {
    public InvalidLoanAmountException(String message) {
        super(message);
    }
}
