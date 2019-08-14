package com.accounting.personal.ontrack.bill;

public class Transaction {
    private final TransactionType transactionType;

    public Transaction(final TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public TransactionType getType() {
        return transactionType;
    }
}
