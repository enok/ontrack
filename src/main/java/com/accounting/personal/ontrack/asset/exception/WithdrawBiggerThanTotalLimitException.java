package com.accounting.personal.ontrack.asset.exception;

public class WithdrawBiggerThanTotalLimitException extends RuntimeException {
    public WithdrawBiggerThanTotalLimitException(String message) {
        super(message);
    }
}
