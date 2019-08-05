package com.accounting.personal.ontrack.asset.exception;

public class NegativeValueDepositedException extends RuntimeException {
    public NegativeValueDepositedException(String message) {
        super(message);
    }
}
