package com.accounting.personal.ontrack.asset.exception;

public class MissingAccountCodeException extends RuntimeException {
    public MissingAccountCodeException(String message) {
        super(message);
    }
}
