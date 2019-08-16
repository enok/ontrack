package com.accounting.personal.ontrack.bill;

import java.time.LocalDate;
import java.util.List;

public class Transaction {
    private final LocalDate date;
    private final TransactionType transactionType;
    private final ExpenseGroup expenseGroup;
    private final String description;

    public Transaction(final LocalDate date, final TransactionType transactionType, final ExpenseGroup expenseGroup,
                       final String description) {
        this.date = date;
        this.transactionType = transactionType;
        this.expenseGroup = expenseGroup;
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public TransactionType getType() {
        return transactionType;
    }

    public String getExpenseGroupName() {
        return expenseGroup.getName();
    }

    public List<String> getExpenseSubGroupNames() {
        return expenseGroup.getSubGroupNames();
    }

    public String getFirstExpenseSubGroupName() {
        return getExpenseSubGroupNames().get(0);
    }

    public String getDescription() {
        return description;
    }
}
