package com.accounting.personal.ontrack.payment.exception;

public class InvalidCvvValue extends RuntimeException {
    public InvalidCvvValue(String message) {
        super(message);
    }
}
