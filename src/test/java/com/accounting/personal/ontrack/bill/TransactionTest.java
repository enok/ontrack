package com.accounting.personal.ontrack.bill;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
        Transaction transaction = new Transaction(getTransactionDate(), TransactionType.MONEY, getExpenseGroup(), getDescription());

        assertThat(getTransactionDate(), is(transaction.getDate()));
    }

    @Test
    public void aTransactionMustHaveAType_Money() {
        Transaction transaction = new Transaction(getTransactionDate(), TransactionType.MONEY, getExpenseGroup(), getDescription());

        assertThat(TransactionType.MONEY, is(transaction.getType()));
    }

    @Test
    public void aTransactionMustHaveAType_Card() {
        Transaction transaction = new Transaction(getTransactionDate(), TransactionType.CARD, getExpenseGroup(), getDescription());

        assertThat(TransactionType.CARD, is(transaction.getType()));
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
        Transaction transaction = new Transaction(getTransactionDate(), TransactionType.MONEY, getExpenseGroup(), getDescription());

        assertThat(getExpenseGroupName(), is(transaction.getExpenseGroupName()));
    }

    @Test
    public void aTransactionMustHaveAExpenseSubGroup() {
        Transaction transaction = new Transaction(getTransactionDate(), TransactionType.MONEY, getExpenseGroup(), getDescription());

        assertThat(getExpenseSubGroupName(), is(transaction.getFirstExpenseSubGroupName()));
    }

    private String getDescription() {
        return "groceries";
    }

    @Test
    public void aTransactionMustHaveADescription() {
        Transaction transaction = new Transaction(getTransactionDate(), TransactionType.MONEY, getExpenseGroup(), getDescription());

        assertThat(getDescription(), is(transaction.getDescription()));
    }
}
