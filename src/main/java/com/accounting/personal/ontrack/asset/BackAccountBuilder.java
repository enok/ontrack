package com.accounting.personal.ontrack.asset;

class BackAccountBuilder {
    private String owner;
    private Double balance;
    private BankBranch bankBranch;
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

    public BackAccountBuilder withBankAgency(final BankBranch bankBranch) {
        this.bankBranch = bankBranch;
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
            return new BankAccount(owner, balance, bankBranch, code, codeDigit, limit);
        }
        return new BankAccount(owner, bankBranch, code, codeDigit, limit);
    }
}
