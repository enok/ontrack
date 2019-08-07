package com.accounting.personal.ontrack.asset.exception;

public class MissingBankAgencyException extends RuntimeException {
    public MissingBankAgencyException(String message) {
        super(message);
    }
}
