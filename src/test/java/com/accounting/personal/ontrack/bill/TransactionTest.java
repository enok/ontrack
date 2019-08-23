package com.accounting.personal.ontrack.bill;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.accounting.personal.ontrack.bill.TransactionMechanism.*;
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
        Transaction transaction = new Transaction(getTransactionDate(), getExpenseGroup(), getDescription(), getValue(), MONEY);

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
        Transaction transaction = new Transaction(getTransactionDate(), getExpenseGroup(), getDescription(), getValue(), MONEY);

        assertThat(getExpenseGroupName(), is(transaction.getExpenseGroupName()));
    }

    @Test
    public void aTransactionMustHaveAExpenseSubGroup() {
        Transaction transaction = new Transaction(getTransactionDate(), getExpenseGroup(), getDescription(), getValue(), MONEY);

        assertThat(getExpenseSubGroupName(), is(transaction.getFirstExpenseSubGroupName()));
    }

    private String getDescription() {
        return "groceries";
    }

    @Test
    public void aTransactionMustHaveADescription() {
        Transaction transaction = new Transaction(getTransactionDate(), getExpenseGroup(), getDescription(), getValue(), MONEY);

        assertThat(getDescription(), is(transaction.getDescription()));
    }

    private Double getValue() {
        return 10.00;
    }

    @Test
    public void aTransactionMustHaveAValue() {
        Transaction transaction = new Transaction(getTransactionDate(), getExpenseGroup(), getDescription(), getValue(), MONEY);

        assertThat(getValue(), is(transaction.getValue()));
    }

    @Test
    public void aTransactionMustHaveAMechanismOfPaymentUsingMoney() {
        Transaction transaction = new Transaction(getTransactionDate(), getExpenseGroup(), getDescription(), getValue(), MONEY);

        assertThat(MONEY, is(transaction.getMechanism()));
    }

    @Test
    public void aTransactionMustHaveAMechanismOfPaymentUsingCard() {
        Transaction transaction = new Transaction(getTransactionDate(), getExpenseGroup(), getDescription(), getValue(), CARD);

        assertThat(CARD, is(transaction.getMechanism()));
    }

    private Integer getCurrentInstallment() {
        return 1;
    }

    @Test
    public void aTransactionCanBeSplittedWithCurrentInstallment() {
        Transaction transaction = new Transaction(getTransactionDate(), getExpenseGroup(), getDescription(), getValue(), CARD, getCurrentInstallment(), getTotalInstallments());

        assertThat(getCurrentInstallment(), is(transaction.getCurrentInstallment()));
    }

    private Integer getTotalInstallments() {
        return 2;
    }

    @Test
    public void aTransactionCanBeSplittedWithTotalInstallments() {
        Transaction transaction = new Transaction(getTransactionDate(), getExpenseGroup(), getDescription(), getValue(), CARD, getCurrentInstallment(), getTotalInstallments());

        assertThat(getTotalInstallments(), is(transaction.getTotalInstallments()));
    }

    @Test(expected = InvalidInstallmentException.class)
    public void aTransactionOfTypeMoneyCanBeSplitted_current_total() {
        new Transaction(getTransactionDate(), getExpenseGroup(), getDescription(), getValue(), MONEY, getCurrentInstallment(), getTotalInstallments());
    }

    @Test(expected = InvalidInstallmentException.class)
    public void aTransactionOfTypeMoneyCanBeSplitted_current() {
        new Transaction(getTransactionDate(), getExpenseGroup(), getDescription(), getValue(), MONEY, getCurrentInstallment(), null);
    }

    @Test(expected = InvalidInstallmentException.class)
    public void aTransactionOfTypeMoneyCanBeSplitted_total() {
        new Transaction(getTransactionDate(), getExpenseGroup(), getDescription(), getValue(), MONEY, null, getTotalInstallments());
    }
}
