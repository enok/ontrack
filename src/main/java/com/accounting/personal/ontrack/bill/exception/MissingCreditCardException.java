package com.accounting.personal.ontrack.bill.exception;

public class MissingCreditCardException extends RuntimeException {
    public MissingCreditCardException(final String message) {
        super(message);
    }
}
