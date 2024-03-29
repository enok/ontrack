package com.accounting.personal.ontrack.bill;

import com.accounting.personal.ontrack.asset.Bank;
import com.accounting.personal.ontrack.bill.exception.MissingCreditCardException;
import com.accounting.personal.ontrack.payment.CreditCard;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.accounting.personal.ontrack.bill.TransactionMechanism.CARD;
import static com.accounting.personal.ontrack.bill.TransactionMechanism.MONEY;
import static com.accounting.personal.ontrack.bill.TransactionType.CREDIT;
import static com.accounting.personal.ontrack.bill.TransactionType.DEBIT;
import static com.accounting.personal.ontrack.payment.CreditCardClass.GOLD;
import static com.accounting.personal.ontrack.payment.CreditCardFlag.VISA;
import static com.accounting.personal.ontrack.payment.CreditCardSubClass.STANDARD;
import static com.accounting.personal.ontrack.payment.CreditCardType.PHYSICAL;
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

    @Test(expected = MissingMandatoryFieldsException.class)
    public void errorWhenMissingMandatoryFields() {
        Transaction
                .createObject()
                .build();
    }

    @Test
    public void aTransactionMustHaveADate() {
        Transaction transaction = Transaction
                .createObject()
                .withDate(getTransactionDate())
                .withExpenseSubGroup(getExpenseSubGroup())
                .withDescription(getDescription())
                .withType(getType())
                .withValue(getValue())
                .withMechanism(MONEY)
                .build();

        assertThat(getTransactionDate(), is(transaction.getDate()));
    }

    private String getExpenseGroupName() {
        return "HOUSE";
    }

    private ExpenseGroup getExpenseGroup() {
        return new ExpenseGroup(getExpenseGroupName());
    }

    private String getExpenseSubGroupName() {
        return "SUPERMARKET";
    }

    private ExpenseSubGroup getExpenseSubGroup() {
        return new ExpenseSubGroup(getExpenseSubGroupName(), getExpenseGroup());
    }

    @Test
    public void aTransactionMustHaveAExpenseSubGroup() {
        Transaction transaction = Transaction
                .createObject()
                .withDate(getTransactionDate())
                .withExpenseSubGroup(getExpenseSubGroup())
                .withDescription(getDescription())
                .withType(getType())
                .withValue(getValue())
                .withMechanism(MONEY)
                .build();

        assertThat(getExpenseSubGroupName(), is(transaction.getExpenseSubGroupName()));
    }

    private String getDescription() {
        return "groceries";
    }

    @Test
    public void aTransactionMustHaveADescription() {
        Transaction transaction = Transaction
                .createObject()
                .withDate(getTransactionDate())
                .withExpenseSubGroup(getExpenseSubGroup())
                .withDescription(getDescription())
                .withType(getType())
                .withValue(getValue())
                .withMechanism(MONEY)
                .build();

        assertThat(getDescription(), is(transaction.getDescription()));
    }

    private TransactionType getType() {
        return DEBIT;
    }

    @Test
    public void aTransactionMustHaveAType_Debit() {
        Transaction transaction = Transaction
                .createObject()
                .withDate(getTransactionDate())
                .withExpenseSubGroup(getExpenseSubGroup())
                .withDescription(getDescription())
                .withType(getType())
                .withValue(getValue())
                .withMechanism(MONEY)
                .build();

        assertThat(getType(), is(transaction.getType()));
    }

    @Test
    public void aTransactionMustHaveAType_Credit() {
        Transaction transaction = Transaction
                .createObject()
                .withDate(getTransactionDate())
                .withExpenseSubGroup(getExpenseSubGroup())
                .withDescription(getDescription())
                .withType(CREDIT)
                .withValue(getValue())
                .withMechanism(MONEY)
                .build();

        assertThat(CREDIT, is(transaction.getType()));
    }

    private Double getValue() {
        return 10.00;
    }

    @Test
    public void aTransactionMustHaveAValue() {
        Transaction transaction = Transaction
                .createObject()
                .withDate(getTransactionDate())
                .withExpenseSubGroup(getExpenseSubGroup())
                .withDescription(getDescription())
                .withType(getType())
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
                .withExpenseSubGroup(getExpenseSubGroup())
                .withDescription(getDescription())
                .withType(getType())
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
                .withExpenseSubGroup(getExpenseSubGroup())
                .withDescription(getDescription())
                .withType(getType())
                .withValue(getValue())
                .withMechanism(CARD)
                .withCreditCard(getCreditCard())
                .build();

        assertThat(CARD, is(transaction.getMechanism()));
    }


    private String getBankName() {
        return "Boston";
    }

    private String getBankCode() {
        return "033";
    }

    private Bank getBank() {
        return new Bank(getBankCode(), getBankName());
    }

    private long getCreditCardNumber() {
        return 123456789012L;
    }

    private String getCustomerName() {
        return "Joseph Smith";
    }

    private String getMemberSince() {
        return "2001-01";
    }

    private LocalDate getMemberSinceDate() {
        return LocalDate.parse(getMemberSince() + "-01");
    }

    private String getValidThru() {
        return "2002-01";
    }

    private LocalDate getValidThruDate() {
        return LocalDate.parse(getValidThru() + "-01");
    }

    private int getCVV() {
        return 123;
    }

    private CreditCard getCreditCard() {
        return CreditCard
                .createObject()
                .withCreditCardClass(GOLD)
                .withCreditCardSubClass(STANDARD)
                .withCreditCardFlag(VISA)
                .withCreditCardType(PHYSICAL)
                .withBank(getBank())
                .withCreditCardNumber(getCreditCardNumber())
                .withCustomerName(getCustomerName())
                .withMemberSinceDate(getMemberSinceDate())
                .withValidThruDate(getValidThruDate())
                .withCVV(getCVV())
                .build();
    }

    @Test(expected = MissingCreditCardException.class)
    public void errorWhenMechanismIsCardAndThereIsNoCreditCardInformation() {
        Transaction transaction = Transaction
                .createObject()
                .withDate(getTransactionDate())
                .withExpenseSubGroup(getExpenseSubGroup())
                .withDescription(getDescription())
                .withType(getType())
                .withValue(getValue())
                .withMechanism(CARD)
                .withCurrentInstallment(getCurrentInstallment())
                .withTotalInstallments(getTotalInstallments())
                .build();

        assertThat(getCurrentInstallment(), is(transaction.getCurrentInstallment()));
    }

    @Test
    public void aTransactionOfTypeCardMustHaveACreditCard() {
        Transaction transaction = Transaction
                .createObject()
                .withDate(getTransactionDate())
                .withExpenseSubGroup(getExpenseSubGroup())
                .withDescription(getDescription())
                .withType(getType())
                .withValue(getValue())
                .withMechanism(CARD)
                .withCreditCard(getCreditCard())
                .withCurrentInstallment(getCurrentInstallment())
                .withTotalInstallments(getTotalInstallments())
                .build();

        assertThat(getCreditCard(), is(transaction.getCreditCard()));
    }

    private Integer getCurrentInstallment() {
        return 1;
    }

    @Test
    public void aTransactionCanBeSplittedWithCurrentInstallment() {
        Transaction transaction = Transaction
                .createObject()
                .withDate(getTransactionDate())
                .withExpenseSubGroup(getExpenseSubGroup())
                .withDescription(getDescription())
                .withType(getType())
                .withValue(getValue())
                .withMechanism(CARD)
                .withCreditCard(getCreditCard())
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
                .withExpenseSubGroup(getExpenseSubGroup())
                .withDescription(getDescription())
                .withType(getType())
                .withValue(getValue())
                .withMechanism(CARD)
                .withCreditCard(getCreditCard())
                .withCurrentInstallment(getCurrentInstallment())
                .withTotalInstallments(getTotalInstallments())
                .build();

        assertThat(getTotalInstallments(), is(transaction.getTotalInstallments()));
    }

    @Test(expected = InvalidInstallmentException.class)
    public void aTransactionOfTypeMoneyCannotBeSplitted_current_total() {
        Transaction
                .createObject()
                .withDate(getTransactionDate())
                .withExpenseSubGroup(getExpenseSubGroup())
                .withDescription(getDescription())
                .withType(getType())
                .withValue(getValue())
                .withMechanism(MONEY)
                .withCurrentInstallment(getCurrentInstallment())
                .withTotalInstallments(getTotalInstallments())
                .build();
    }

    @Test(expected = InvalidInstallmentException.class)
    public void aTransactionOfTypeMoneyCannotBeSplitted_current() {
        Transaction
                .createObject()
                .withDate(getTransactionDate())
                .withExpenseSubGroup(getExpenseSubGroup())
                .withDescription(getDescription())
                .withType(getType())
                .withValue(getValue())
                .withMechanism(MONEY)
                .withCurrentInstallment(getCurrentInstallment())
                .withTotalInstallments(null)
                .build();
    }

    @Test(expected = InvalidInstallmentException.class)
    public void aTransactionOfTypeMoneyCannotBeSplitted_total() {
        Transaction
                .createObject()
                .withDate(getTransactionDate())
                .withExpenseSubGroup(getExpenseSubGroup())
                .withDescription(getDescription())
                .withType(getType())
                .withValue(getValue())
                .withMechanism(MONEY)
                .withCurrentInstallment(null)
                .withTotalInstallments(getTotalInstallments())
                .build();
    }
}
