package com.accounting.personal.ontrack.asset;

// TODO create tests
public class BankAgency {
    private final int code;
    private final Integer digit;

    public BankAgency(final int code) {
        this.code = code;
        this.digit = null;
    }

    public BankAgency(int code, int digit) {
        this.code = code;
        this.digit = digit;
    }

    public int getCode() {
        return code;
    }

    public Integer getDigit() {
        return digit;
    }
}
