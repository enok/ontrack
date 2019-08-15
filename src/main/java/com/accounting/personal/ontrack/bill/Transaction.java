package com.accounting.personal.ontrack.bill;

public class Transaction {
    private final TransactionType transactionType;
    private final ExpenseGroup expenseGroup;

    public Transaction(final TransactionType transactionType, final ExpenseGroup expenseGroup) {
        this.transactionType = transactionType;
        this.expenseGroup = expenseGroup;
    }

    public TransactionType getType() {
        return transactionType;
    }

    public String getExpenseGroupName() {
        return expenseGroup.getName();
    }
}
