package com.accounting.personal.ontrack.bill;

import java.time.LocalDate;
import java.util.List;

import static com.accounting.personal.ontrack.bill.TransactionMechanism.MONEY;

public class Transaction {
    private final LocalDate date;
    private final ExpenseGroup expenseGroup;
    private final String description;
    private final TransactionType type;
    private final Double value;
    private final TransactionMechanism mechanism;
    private final Integer currentInstallment;
    private final Integer totalInstallments;

    Transaction(final LocalDate date, final ExpenseGroup expenseGroup, final String description,
                final TransactionType type, final Double value, final TransactionMechanism mechanism) {
        this(date, expenseGroup, description, type, value, mechanism, null, null);
    }

    Transaction(final LocalDate date, final ExpenseGroup expenseGroup, final String description,
                final TransactionType type, final Double value, final TransactionMechanism mechanism,
                final Integer currentInstallment, final Integer totalInstallments) {
        this.date = date;
        this.expenseGroup = expenseGroup;
        this.description = description;
        this.type = type;
        this.value = value;
        this.mechanism = mechanism;
        this.currentInstallment = currentInstallment;
        this.totalInstallments = totalInstallments;
        validateInstallment();
    }

    public LocalDate getDate() {
        return date;
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

    public TransactionType getType() {
        return type;
    }

    public Double getValue() {
        return value;
    }

    public TransactionMechanism getMechanism() {
        return mechanism;
    }

    public Integer getCurrentInstallment() {
        return currentInstallment;
    }

    public Integer getTotalInstallments() {
        return totalInstallments;
    }

    public static TransactionBuilder createObject() {
        return new TransactionBuilder();
    }

    private void validateInstallment() {
        if (typeMoneyWithInstallment()) {
            throw new InvalidInstallmentException("A money transaction cannot be splitted");
        }
    }

    private boolean typeMoneyWithInstallment() {
        return MONEY.equals(mechanism) &&
                ((currentInstallment != null) || (totalInstallments != null));
    }
}
