package com.accounting.personal.ontrack.payment;

import com.accounting.personal.ontrack.asset.Bank;

import java.time.LocalDate;

class CreditCardBuilder {

    private CreditCardClass creditCardClass;
    private CreditCardSubClass creditCardSubClass;
    private CreditCardFlag creditCardFlag;
    private CreditCardType creditCardType;
    private Bank bank;
    private long creditCardNumber;
    private String customerName;
    private LocalDate memberSinceDate;
    private LocalDate validThruDate;
    private int cvv;

    public CreditCardBuilder withCreditCardClass(final CreditCardClass creditCardClass) {
        this.creditCardClass = creditCardClass;
        return this;
    }

    public CreditCardBuilder withCreditCardSubClass(final CreditCardSubClass creditCardSubClass) {
        this.creditCardSubClass = creditCardSubClass;
        return this;
    }

    public CreditCardBuilder withCreditCardFlag(final CreditCardFlag creditCardFlag) {
        this.creditCardFlag = creditCardFlag;
        return this;
    }

    public CreditCardBuilder withCreditCardType(final CreditCardType creditCardType) {
        this.creditCardType = creditCardType;
        return this;
    }

    public CreditCardBuilder withBank(final Bank bank) {
        this.bank = bank;
        return this;
    }

    public CreditCardBuilder withCreditCardNumber(final long creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
        return this;
    }

    public CreditCardBuilder withCustomerName(final String customerName) {
        this.customerName = customerName;
        return this;
    }

    public CreditCardBuilder withMemberSinceDate(final LocalDate memberSinceDate) {
        this.memberSinceDate = memberSinceDate;
        return this;
    }

    public CreditCardBuilder withValidThruDate(final LocalDate validThruDate) {
        this.validThruDate = validThruDate;
        return this;
    }

    public CreditCardBuilder withCVV(final int cvv) {
        this.cvv = cvv;
        return this;
    }

    public CreditCard build() {
        return new CreditCard(creditCardClass, creditCardSubClass, creditCardFlag, creditCardType, bank,
                creditCardNumber, customerName, memberSinceDate, validThruDate, cvv);
    }
}
