package com.accounting.personal.ontrack.bill;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.accounting.personal.ontrack.bill.TransactionMechanism.CARD;
import static com.accounting.personal.ontrack.bill.TransactionMechanism.MONEY;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionTest {

    private LocalDate getTransactionDate() {
        String dateInString = "2019/01/01";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return LocalDate.parse(dateInString, formatter);
    }

    @Test
    public void aTransactionMustHaveADate() {
        Transaction transaction = Transaction
                .createObject()
                .withDate(getTransactionDate())
                .withExpenseGroup(getExpenseGroup())
                .withDescription(getDescription())
                .withValue(getValue())
                .withMechanism(MONEY)
                .build();

        assertThat(getTransactionDate(), is(transaction.getDate()));
    }

    private String getExpenseSubGroupName() {
        return "SUPERMARKET";
    }

    private ExpenseSubGroup getExpenseSubGroup() {
        return new ExpenseSubGroup(getExpenseSubGroupName());
    }

    private String getExpenseGroupName() {
        return "HOUSE";
    }

    private ExpenseGroup getExpenseGroup() {
        return new ExpenseGroup(getExpenseGroupName(), getExpenseSubGroup());
    }

    @Test
    public void aTransactionMustHaveAExpenseGroup() {
        Transaction transaction = Transaction
                .createObject()
                .withDate(getTransactionDate())
                .withExpenseGroup(getExpenseGroup())
                .withDescription(getDescription())
                .withValue(getValue())
                .withMechanism(MONEY)
                .build();

        assertThat(getExpenseGroupName(), is(transaction.getExpenseGroupName()));
    }

    @Test
    public void aTransactionMustHaveAExpenseSubGroup() {
        Transaction transaction = Transaction
                .createObject()
                .withDate(getTransactionDate())
                .withExpenseGroup(getExpenseGroup())
                .withDescription(getDescription())
                .withValue(getValue())
                .withMechanism(MONEY)
                .build();

        assertThat(getExpenseSubGroupName(), is(transaction.getFirstExpenseSubGroupName()));
    }

    private String getDescription() {
        return "groceries";
    }

    @Test
    public void aTransactionMustHaveADescription() {
        Transaction transaction = Transaction
                .createObject()
                .withDate(getTransactionDate())
                .withExpenseGroup(getExpenseGroup())
                .withDescription(getDescription())
                .withValue(getValue())
                .withMechanism(MONEY)
                .build();

        assertThat(getDescription(), is(transaction.getDescription()));
    }

    private Double getValue() {
        return 10.00;
    }

    @Test
    public void aTransactionMustHaveAValue() {
        Transaction transaction = Transaction
                .createObject()
                .withDate(getTransactionDate())
                .withExpenseGroup(getExpenseGroup())
                .withDescription(getDescription())
                .withValue(getValue())
                .withMechanism(MONEY)
                .build();

        assertThat(getValue(), is(transaction.getValue()));
    }

    @Test
    public void aTransactionMustHaveAMechanismOfPaymentUsingMoney() {
        Transaction transaction = Transaction
                .createObject()
                .withDate(getTransactionDate())
                .withExpenseGroup(getExpenseGroup())
                .withDescription(getDescription())
                .withValue(getValue())
                .withMechanism(MONEY)
                .build();

        assertThat(MONEY, is(transaction.getMechanism()));
    }

    @Test
    public void aTransactionMustHaveAMechanismOfPaymentUsingCard() {
        Transaction transaction = Transaction
                .createObject()
                .withDate(getTransactionDate())
                .withExpenseGroup(getExpenseGroup())
                .withDescription(getDescription())
                .withValue(getValue())
                .withMechanism(CARD)
                .build();

        assertThat(CARD, is(transaction.getMechanism()));
    }

    private Integer getCurrentInstallment() {
        return 1;
    }

    @Test
    public void aTransactionCanBeSplittedWithCurrentInstallment() {
        Transaction transaction = Transaction
                .createObject()
                .withDate(getTransactionDate())
                .withExpenseGroup(getExpenseGroup())
                .withDescription(getDescription())
                .withValue(getValue())
                .withMechanism(CARD)
                .withCurrentInstallment(getCurrentInstallment())
                .withTotalInstallments(getTotalInstallments())
                .build();

        assertThat(getCurrentInstallment(), is(transaction.getCurrentInstallment()));
    }

    private Integer getTotalInstallments() {
        return 2;
    }

    @Test
    public void aTransactionCanBeSplittedWithTotalInstallments() {
        Transaction transaction = Transaction
                .createObject()
                .withDate(getTransactionDate())
                .withExpenseGroup(getExpenseGroup())
                .withDescription(getDescription())
                .withValue(getValue())
                .withMechanism(CARD)
                .withCurrentInstallment(getCurrentInstallment())
                .withTotalInstallments(getTotalInstallments())
                .build();

        assertThat(getTotalInstallments(), is(transaction.getTotalInstallments()));
    }

    @Test(expected = InvalidInstallmentException.class)
    public void aTransactionOfTypeMoneyCannotBeSplitted_current_total() {
        Transaction transaction = Transaction
                .createObject()
                .withDate(getTransactionDate())
                .withExpenseGroup(getExpenseGroup())
                .withDescription(getDescription())
                .withValue(getValue())
                .withMechanism(MONEY)
                .withCurrentInstallment(getCurrentInstallment())
                .withTotalInstallments(getTotalInstallments())
                .build();
    }

    @Test(expected = InvalidInstallmentException.class)
    public void aTransactionOfTypeMoneyCannotBeSplitted_current() {
        Transaction transaction = Transaction
                .createObject()
                .withDate(getTransactionDate())
                .withExpenseGroup(getExpenseGroup())
                .withDescription(getDescription())
                .withValue(getValue())
                .withMechanism(MONEY)
                .withCurrentInstallment(getCurrentInstallment())
                .withTotalInstallments(null)
                .build();
    }

    @Test(expected = InvalidInstallmentException.class)
    public void aTransactionOfTypeMoneyCannotBeSplitted_total() {
        Transaction transaction = Transaction
                .createObject()
                .withDate(getTransactionDate())
                .withExpenseGroup(getExpenseGroup())
                .withDescription(getDescription())
                .withValue(getValue())
                .withMechanism(MONEY)
                .withCurrentInstallment(null)
                .withTotalInstallments(getTotalInstallments())
                .build();
    }
}
