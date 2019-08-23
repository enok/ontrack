package com.accounting.personal.ontrack.payment;

import com.accounting.personal.ontrack.asset.Bank;
import com.accounting.personal.ontrack.payment.exception.InvalidCvvValue;

import java.time.LocalDate;
import java.util.List;

public class CreditCard {
    private final CreditCardClass creditCardClass;
    private final CreditCardSubClass creditCardSubClass;
    private final CreditCardFlag creditCardFlag;
    private final CreditCardType creditCardType;
    private final Bank bank;
    private final long creditCardNumber;
    private final String customerName;
    private final LocalDate memberSince;
    private final LocalDate validThru;
    private final int cvv;
    private final List<CreditCardFunction> creditCardFunctions;

    CreditCard(final CreditCardClass creditCardClass, final CreditCardSubClass creditCardSubClass,
                      final CreditCardFlag creditCardFlag, final CreditCardType creditCardType, final Bank bank,
                      final long creditCardNumber, final String customerName, final LocalDate memberSince,
                      final LocalDate validThru, final int cvv, final List<CreditCardFunction> creditCardFunctions) {
        this.creditCardClass = creditCardClass;
        this.creditCardSubClass = creditCardSubClass;
        this.creditCardFlag = creditCardFlag;
        this.creditCardType = creditCardType;
        this.bank = bank;
        this.creditCardNumber = creditCardNumber;
        this.customerName = customerName;
        this.memberSince = memberSince;
        this.validThru = validThru;
        validateCvv(cvv);
        this.cvv = cvv;
        this.creditCardFunctions = creditCardFunctions;
    }

    private void validateCvv(int cvv) {
        int numberOfDigits = (int) (Math.log10(cvv) + 1);
        if (3 != numberOfDigits) {
            throw new InvalidCvvValue("Cvv has " + numberOfDigits + " digits. Should be 3.");
        }
    }

    public CreditCardClass getClass_() {
        return creditCardClass;
    }

    public CreditCardSubClass getSubClass() {
        return creditCardSubClass;
    }

    public CreditCardFlag getFlag() {
        return creditCardFlag;
    }

    public CreditCardType getType() {
        return creditCardType;
    }

    public Bank getBank() {
        return bank;
    }

    public Long getNumber() {
        return creditCardNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public LocalDate getMemberSince() {
        return memberSince;
    }

    public LocalDate getValidThru() {
        return validThru;
    }

    public int getCVV() {
        return cvv;
    }

    public List<CreditCardFunction> getFunctions() {
        return creditCardFunctions;
    }

    public static CreditCardBuilder createObject() {
        return new CreditCardBuilder();
    }
}
