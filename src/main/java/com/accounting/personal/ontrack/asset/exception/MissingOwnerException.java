package com.accounting.personal.ontrack.asset.exception;

public class MissingOwnerException extends RuntimeException {
    public MissingOwnerException(String message) {
        super(message);
    }
}
