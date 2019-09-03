package com.accounting.personal.ontrack.bill;

import com.accounting.personal.ontrack.bill.exception.MissingCreditCardException;
import com.accounting.personal.ontrack.payment.CreditCard;

import java.time.LocalDate;

import static com.accounting.personal.ontrack.bill.TransactionMechanism.CARD;
import static com.accounting.personal.ontrack.bill.TransactionMechanism.MONEY;

public class Transaction {
    private final LocalDate date;
    private final ExpenseSubGroup expenseSubGroup;
    private final String description;
    private final TransactionType type;
    private final Double value;
    private final TransactionMechanism mechanism;
    private final CreditCard creditCard;
    private final Integer currentInstallment;
    private final Integer totalInstallments;

    Transaction(final LocalDate date, final ExpenseSubGroup expenseSubGroup, final String description,
                final TransactionType type, final Double value, final TransactionMechanism mechanism) {
        this(date, expenseSubGroup, description, type, value, mechanism, null, null, null);
    }

    Transaction(final LocalDate date, final ExpenseSubGroup expenseSubGroup, final String description,
                final TransactionType type, final Double value, final TransactionMechanism mechanism,
                final CreditCard creditCard) {
        this(date, expenseSubGroup, description, type, value, mechanism, creditCard, null, null);
    }

    Transaction(final LocalDate date, final ExpenseSubGroup expenseSubGroup, final String description,
                final TransactionType type, final Double value, final TransactionMechanism mechanism,
                final CreditCard creditCard, final Integer currentInstallment, final Integer totalInstallments) {
        this.date = date;
        this.expenseSubGroup = expenseSubGroup;
        this.description = description;
        this.type = type;
        this.value = value;
        this.mechanism = mechanism;
        this.creditCard = creditCard;
        this.currentInstallment = currentInstallment;
        this.totalInstallments = totalInstallments;
        validateCreditCard();
        validateInstallment();
    }

    public LocalDate getDate() {
        return date;
    }

    public String getExpenseSubGroupName() {
        return expenseSubGroup.getName();
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

    public CreditCard getCreditCard() {
        return creditCard;
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

    private void validateCreditCard() {
        if (CARD.equals(mechanism) && (creditCard == null)) {
            throw new MissingCreditCardException("Transaction using credit card, but no credit card information provided.");
        }
    }

    private boolean typeMoneyWithInstallment() {
        return MONEY.equals(mechanism) &&
                ((currentInstallment != null) || (totalInstallments != null));
    }

    private void validateInstallment() {
        if (typeMoneyWithInstallment()) {
            throw new InvalidInstallmentException("A money transaction cannot be splitted");
        }
    }
}
