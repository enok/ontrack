package com.accounting.personal.ontrack.asset;

import com.accounting.personal.ontrack.asset.exception.MissingBankException;

public class BankAgency {
    private final Bank bank;
    private final int code;
    private final Integer digit;

    public BankAgency(Bank bank, final int code) {
        validateBank(bank);
        this.bank = bank;
        this.code = code;
        this.digit = null;
    }

    public BankAgency(Bank bank, int code, int digit) {
        this.bank = bank;
        this.code = code;
        this.digit = digit;
    }

    private void validateBank(Bank bank) {
        if (bank == null) {
            throw new MissingBankException("A bank account must have a bank associated with it.");
        }
    }

    public String bankName() {
        return bank.getName();
    }

    public int bankCode() {
        return bank.getCode();
    }

    public int getCode() {
        return code;
    }

    public Integer getDigit() {
        return digit;
    }
}
