package com.accounting.personal.ontrack.bill;

public class InvalidInstallmentException extends RuntimeException {
    public InvalidInstallmentException(final String message) {
        super(message);
    }
}
