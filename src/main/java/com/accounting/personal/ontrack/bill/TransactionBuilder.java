package com.accounting.personal.ontrack.bill;

import java.time.LocalDate;

public class TransactionBuilder {
    private LocalDate date;
    private ExpenseGroup expenseGroup;
    private String description;
    private Double value;
    private TransactionMechanism mechanism;
    private Integer currentInstallment;
    private Integer totalInstallments;

    public TransactionBuilder withDate(final LocalDate date) {
        this.date = date;
        return this;
    }

    public TransactionBuilder withExpenseGroup(final ExpenseGroup expenseGroup) {
        this.expenseGroup = expenseGroup;
        return this;
    }

    public TransactionBuilder withDescription(final String description) {
        this.description = description;
        return this;
    }

    public TransactionBuilder withValue(final Double value) {
        this.value = value;
        return this;
    }

    public TransactionBuilder withMechanism(final TransactionMechanism mechanism) {
        this.mechanism = mechanism;
        return this;
    }

    public TransactionBuilder withCurrentInstallment(final Integer currentInstallment) {
        this.currentInstallment = currentInstallment;
        return this;
    }

    public TransactionBuilder withTotalInstallments(final Integer totalInstallments) {
        this.totalInstallments = totalInstallments;
        return this;
    }

    public Transaction build() {
        if ((currentInstallment == null) && (totalInstallments == null)) {
            return new Transaction(date, expenseGroup, description, value, mechanism);
        }
        return new Transaction(date, expenseGroup, description, value, mechanism, currentInstallment, totalInstallments);
    }
}