package com.accounting.personal.ontrack.asset.exception;

public class MissingBankBranchException extends RuntimeException {
    public MissingBankBranchException(String message) {
        super(message);
    }
}
