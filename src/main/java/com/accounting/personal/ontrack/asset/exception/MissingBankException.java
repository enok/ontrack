package com.accounting.personal.ontrack.asset.exception;

public class MissingBankException extends RuntimeException {
    public MissingBankException(String message) {
        super(message);
    }
}
