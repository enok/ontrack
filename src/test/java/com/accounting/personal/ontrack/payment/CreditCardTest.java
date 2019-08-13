package com.accounting.personal.ontrack.payment;

import com.accounting.personal.ontrack.asset.Bank;
import com.accounting.personal.ontrack.payment.exception.InvalidCvvValue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static com.accounting.personal.ontrack.payment.CreditCardClass.*;
import static com.accounting.personal.ontrack.payment.CreditCardFlag.MASTERCARD;
import static com.accounting.personal.ontrack.payment.CreditCardFlag.VISA;
import static com.accounting.personal.ontrack.payment.CreditCardFunction.CREDIT;
import static com.accounting.personal.ontrack.payment.CreditCardFunction.DEBIT;
import static com.accounting.personal.ontrack.payment.CreditCardSubClass.*;
import static com.accounting.personal.ontrack.payment.CreditCardType.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CreditCardTest {

    @Test
    public void aCreditCardMustHaveAClass_Gold() {
        CreditCard creditCard = CreditCard
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

        assertThat(GOLD, is(creditCard.getClass_()));
    }

    @Test
    public void aCreditCardMustHaveAClass_Platinum() {
        CreditCard creditCard = CreditCard
                .createObject()
                .withCreditCardClass(PLATINUM)
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

        assertThat(PLATINUM, is(creditCard.getClass_()));
    }

    @Test
    public void aCreditCardMustHaveAClass_Black() {
        CreditCard creditCard = CreditCard
                .createObject()
                .withCreditCardClass(BLACK)
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

        assertThat(BLACK, is(creditCard.getClass_()));
    }

    @Test
    public void aCreditCardMustHaveASubClass_Standard() {
        CreditCard creditCard = CreditCard
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

        assertThat(STANDARD, is(creditCard.getSubClass()));
    }

    @Test
    public void aCreditCardMustHaveASubClass_Unique() {
        CreditCard creditCard = CreditCard
                .createObject()
                .withCreditCardClass(GOLD)
                .withCreditCardSubClass(UNIQUE)
                .withCreditCardFlag(VISA)
                .withCreditCardType(PHYSICAL)
                .withBank(getBank())
                .withCreditCardNumber(getCreditCardNumber())
                .withCustomerName(getCustomerName())
                .withMemberSinceDate(getMemberSinceDate())
                .withValidThruDate(getValidThruDate())
                .withCVV(getCVV())
                .build();

        assertThat(UNIQUE, is(creditCard.getSubClass()));
    }

    @Test
    public void aCreditCardMustHaveASubClass_Infinity() {
        CreditCard creditCard = CreditCard
                .createObject()
                .withCreditCardClass(GOLD)
                .withCreditCardSubClass(INFINITY)
                .withCreditCardFlag(VISA)
                .withCreditCardType(PHYSICAL)
                .withBank(getBank())
                .withCreditCardNumber(getCreditCardNumber())
                .withCustomerName(getCustomerName())
                .withMemberSinceDate(getMemberSinceDate())
                .withValidThruDate(getValidThruDate())
                .withCVV(getCVV())
                .build();

        assertThat(INFINITY, is(creditCard.getSubClass()));
    }

    @Test
    public void aCreditCardMustHaveAFlag_Visa() {
        CreditCard creditCard = CreditCard
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

        assertThat(VISA, is(creditCard.getFlag()));
    }

    @Test
    public void aCreditCardMustHaveAFlag_Mastercard() {
        CreditCard creditCard = CreditCard
                .createObject()
                .withCreditCardClass(GOLD)
                .withCreditCardSubClass(STANDARD)
                .withCreditCardFlag(MASTERCARD)
                .withCreditCardType(PHYSICAL)
                .withBank(getBank())
                .withCreditCardNumber(getCreditCardNumber())
                .withCustomerName(getCustomerName())
                .withMemberSinceDate(getMemberSinceDate())
                .withValidThruDate(getValidThruDate())
                .withCVV(getCVV())
                .build();

        assertThat(MASTERCARD, is(creditCard.getFlag()));
    }

    @Test
    public void aCreditCardMustHaveAType_Physical() {
        CreditCard creditCard = CreditCard
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

        assertThat(PHYSICAL, is(creditCard.getType()));
    }

    @Test
    public void aCreditCardMustHaveAType_Online() {
        CreditCard creditCard = CreditCard
                .createObject()
                .withCreditCardClass(GOLD)
                .withCreditCardSubClass(STANDARD)
                .withCreditCardFlag(VISA)
                .withCreditCardType(ONLINE)
                .withBank(getBank())
                .withCreditCardNumber(getCreditCardNumber())
                .withCustomerName(getCustomerName())
                .withMemberSinceDate(getMemberSinceDate())
                .withValidThruDate(getValidThruDate())
                .withCVV(getCVV())
                .build();

        assertThat(ONLINE, is(creditCard.getType()));
    }

    @Test
    public void aCreditCardMustHaveAType_NFC() {
        CreditCard creditCard = CreditCard
                .createObject()
                .withCreditCardClass(GOLD)
                .withCreditCardSubClass(STANDARD)
                .withCreditCardFlag(VISA)
                .withCreditCardType(NFC)
                .withBank(getBank())
                .withCreditCardNumber(getCreditCardNumber())
                .withCustomerName(getCustomerName())
                .withMemberSinceDate(getMemberSinceDate())
                .withValidThruDate(getValidThruDate())
                .withCVV(getCVV())
                .build();

        assertThat(NFC, is(creditCard.getType()));
    }

    private String getBankName() {
        return "Boston";
    }

    private int getBankCode() {
        return 33;
    }

    private Bank getBank() {
        return new Bank(getBankName(), getBankCode());
    }

    @Test
    public void aCreditCardMustHaveABank() {
        CreditCard creditCard = CreditCard
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

        assertThat(getBank(), is(creditCard.getBank()));
    }

    private long getCreditCardNumber() {
        return 123456789012L;
    }

    @Test
    public void aCreditCardMustHaveANumber() {
        CreditCard creditCard = CreditCard
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

        assertThat(getCreditCardNumber(), is(creditCard.getNumber()));
    }

    private String getCustomerName() {
        return "Joseph Smith";
    }

    @Test
    public void aCreditCardMustHaveAName() {
        CreditCard creditCard = CreditCard
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

        assertThat(getCustomerName(), is(creditCard.getCustomerName()));
    }

    private String getMemberSince() {
        return "2001-01";
    }

    private LocalDate getMemberSinceDate() {
        return LocalDate.parse(getMemberSince() + "-01");
    }

    @Test
    public void aCreditCardMustHaveAMemberSinceDate() {
        CreditCard creditCard = CreditCard
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

        assertThat(getMemberSinceDate(), is(creditCard.getMemberSince()));
    }

    private String getValidThru() {
        return "2002-01";
    }

    private LocalDate getValidThruDate() {
        return LocalDate.parse(getValidThru() + "-01");
    }

    @Test
    public void aCreditCardMustHaveAValidThruDate() {
        CreditCard creditCard = CreditCard
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

        assertThat(getValidThruDate(), is(creditCard.getValidThru()));
    }

    @Test(expected = InvalidCvvValue.class)
    public void errorWhenCVVHasInvalidValue() {
        CreditCard
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
                .withCVV(1234)
                .build();
    }

    private int getCVV() {
        return 123;
    }

    @Test
    public void aCreditCardMustHaveACVV() {
        CreditCard creditCard = CreditCard
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

        assertThat(getCVV(), is(creditCard.getCVV()));
    }

    @Test
    public void aCreditCardMustHaveAFunction_Credit() {
        CreditCard creditCard = CreditCard
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
                .withFunction(CREDIT)
                .build();

        assertThat(CREDIT, is(creditCard.getFunctions().get(0)));
    }

    @Test
    public void aCreditCardMustHaveAFunction_Debit() {
        CreditCard creditCard = CreditCard
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
                .withFunction(DEBIT)
                .build();

        assertThat(DEBIT, is(creditCard.getFunctions().get(0)));
    }

    @Test
    public void aCreditCardMustHaveAFunction_Credit_Debit() {
        CreditCard creditCard = CreditCard
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
                .withFunction(CREDIT)
                .withFunction(DEBIT)
                .build();

        assertThat(CREDIT, is(creditCard.getFunctions().get(0)));
        assertThat(DEBIT, is(creditCard.getFunctions().get(1)));
    }
}