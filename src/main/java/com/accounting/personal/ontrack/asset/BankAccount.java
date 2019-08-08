package com.accounting.personal.ontrack.asset;

import com.accounting.personal.ontrack.asset.exception.*;

public class BankAccount extends Wallet {
    private final BankAgency bankAgency;
    private final Integer code;
    private final Integer codeDigit;
    private Double limit;

    public BankAccount(final String owner, final double balance, final BankAgency bankAgency,
                       final Integer code, final Integer codeDigit, Double limit) {
        super(owner, balance);
        validateBankAgency(bankAgency);
        validateCode(code);
        validateLimit(limit);
        this.bankAgency = bankAgency;
        this.code = code;
        this.codeDigit = codeDigit;
        this.limit = limit;
    }

    public BankAccount(final String owner, final BankAgency bankAgency, final Integer code,
                       final Integer codeDigit, final Double limit) {
        this(owner, 0.0, bankAgency, code, codeDigit, limit);
    }

    @Override
    public BankAccount withdraw(double money) {
        Wallet wallet = super.withdraw(money);
        if (money > totalBalance()) {
            throw new WithdrawBiggerThanTotalLimitException("Cannot withdraw a value bigger than balance + limit.");
        }
        return new BankAccount(wallet.owner(), wallet.balance(), bankAgency, code, codeDigit, limit);
    }

    public int agencyCode() {
        return bankAgency.getCode();
    }

    public Integer agencyDigit() {
        return bankAgency.getDigit();
    }

    public Integer code() {
        return code;
    }

    public Integer codeDigit() {
        return codeDigit;
    }

    public Double limit() {
        return limit;
    }

    public Double totalBalance() {
        return balance() + limit;
    }

    public static BackAccountBuilder createObject() {
        return new BackAccountBuilder();
    }

    private void validateBankAgency(BankAgency bankAgency) {
        if (bankAgency == null) {
            throw new MissingBankAgencyException("A bank account must have a bank agency associated with it.");
        }
    }

    private void validateCode(Integer code) {
        if (code == null) {
            throw new MissingAccountCodeException("An account must have a code.");
        }
    }

    private void validateLimit(Double limit) {
        if (limit == null) {
            throw new MissingAccountLimitException("An account must have a limit.");
        }
    }
}
