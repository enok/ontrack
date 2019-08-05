package com.accounting.personal.ontrack.asset.exception;

public class EmptyWalletException extends RuntimeException {
    public EmptyWalletException(String message) {
        super(message);
    }
}
