package com.accounting.personal.ontrack.asset;

public class BackAccountBuilder {
    private String owner;
    private Double balance;
    private Bank bank;
    private BankAgency bankAgency;
    private Integer code;
    private Integer codeDigit;
    private Double limit;

    public BackAccountBuilder withOwner(final String owner) {
        this.owner = owner;
        return this;
    }

    public BackAccountBuilder withBalance(final double balance) {
        this.balance = balance;
        return this;
    }

    public BackAccountBuilder withBank(final Bank bank) {
        this.bank = bank;
        return this;
    }

    public BackAccountBuilder withBankAgency(final BankAgency bankAgency) {
        this.bankAgency = bankAgency;
        return this;
    }

    public BackAccountBuilder withCode(final Integer code) {
        this.code = code;
        return this;
    }

    public BackAccountBuilder withCodeDigit(final Integer codeDigit) {
        this.codeDigit = codeDigit;
        return this;
    }

    public BackAccountBuilder withLimit(Double limit) {
        this.limit = limit;
        return this;
    }

    public BankAccount build() {
        if (balance != null) {
            return new BankAccount(owner, balance, bank, bankAgency, code, codeDigit, limit);
        }
        return new BankAccount(owner, bank, bankAgency, code, codeDigit, limit);
    }
}
