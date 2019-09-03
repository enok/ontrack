package com.accounting.personal.ontrack.asset;

import com.accounting.personal.ontrack.asset.exception.*;

public class BankAccount extends Wallet {
    private final BankBranch bankBranch;
    private final Integer code;
    private final Integer codeDigit;
    private Double limit;

    public BankAccount(final String owner, final double balance, final BankBranch bankBranch,
                       final Integer code, final Integer codeDigit, Double limit) {
        super(owner, balance);
        validateBankAgency(bankBranch);
        validateCode(code);
        validateLimit(limit);
        this.bankBranch = bankBranch;
        this.code = code;
        this.codeDigit = codeDigit;
        this.limit = limit;
    }

    public BankAccount(final String owner, final BankBranch bankBranch, final Integer code,
                       final Integer codeDigit, final Double limit) {
        this(owner, 0.0, bankBranch, code, codeDigit, limit);
    }

    @Override
    public BankAccount withdraw(double money) {
        Wallet wallet = super.withdraw(money);
        if (money > totalBalance()) {
            throw new WithdrawBiggerThanTotalLimitException("Cannot withdraw a value bigger than balance + limit.");
        }
        return new BankAccount(wallet.owner(), wallet.balance(), bankBranch, code, codeDigit, limit);
    }

    public int agencyCode() {
        return bankBranch.getCode();
    }

    public Integer agencyDigit() {
        return bankBranch.getDigit();
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

    private void validateBankAgency(BankBranch bankBranch) {
        if (bankBranch == null) {
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
