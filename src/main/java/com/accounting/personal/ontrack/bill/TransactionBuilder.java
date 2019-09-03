package com.accounting.personal.ontrack.bill;

import com.accounting.personal.ontrack.bill.exception.MissingCreditCardException;
import com.accounting.personal.ontrack.payment.CreditCard;

import java.time.LocalDate;

import static com.accounting.personal.ontrack.bill.TransactionMechanism.*;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class TransactionBuilder {
    private LocalDate date;
    private ExpenseSubGroup expenseSubGroup;
    private String description;
    private TransactionType type;
    private Double value;
    private TransactionMechanism mechanism;
    private CreditCard creditCard;
    private Integer currentInstallment;
    private Integer totalInstallments;

    public TransactionBuilder withDate(final LocalDate date) {
        this.date = date;
        return this;
    }

    public TransactionBuilder withExpenseSubGroup(final ExpenseSubGroup expenseSubGroup) {
        this.expenseSubGroup = expenseSubGroup;
        return this;
    }

    public TransactionBuilder withDescription(final String description) {
        this.description = description;
        return this;
    }

    public TransactionBuilder withType(final TransactionType type) {
        this.type = type;
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

    public TransactionBuilder withCreditCard(final CreditCard creditCard) {
        this.creditCard = creditCard;
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
        if ((date == null) || (expenseSubGroup == null) || isBlank(description) || (type == null) || (value == null) ||
                (mechanism == null)) {
            throw new MissingMandatoryFieldsException("Missing mandatory field(s).");
        }
        if ((currentInstallment == null) && (totalInstallments == null)) {
            if (creditCard == null) {
                return new Transaction(date, expenseSubGroup, description, type, value, mechanism);
            }
            return new Transaction(date, expenseSubGroup, description, type, value, mechanism, creditCard);
        }
        return new Transaction(date, expenseSubGroup, description, type, value, mechanism, creditCard,
                currentInstallment, totalInstallments);
    }
}