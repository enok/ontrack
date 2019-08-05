package com.accounting.personal.ontrack.asset.exception;

public class NegativeValueWithdrawnException extends RuntimeException {
    public NegativeValueWithdrawnException(String message) {
        super(message);
    }
}
