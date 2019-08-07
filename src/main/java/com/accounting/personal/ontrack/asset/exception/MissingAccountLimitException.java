package com.accounting.personal.ontrack.asset.exception;

public class MissingAccountLimitException extends RuntimeException {
    public MissingAccountLimitException(String message) {
        super(message);
    }
}
