package com.accounting.personal.ontrack.asset;

import com.accounting.personal.ontrack.asset.exception.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BankAccountTests {

    // owner

    private String getOwnerName() {
        return "Bill";
    }

    @Test(expected = MissingNameException.class)
    public void errorWhenABankAccountDoesNotHaveAnOwner() {
        BankAccount
                .createObject()
                .build();
    }

    @Test
    public void aBankAccountMustHaveAnOwner() {
        BankAccount bankAccount = BankAccount
                .createObject()
                .withOwner(getOwnerName())
                .withBankAgency(getBankAgency())
                .withCode(getAccountCode())
                .withLimit(getAccountLimit())
                .build();

        assertThat(getOwnerName(), is(bankAccount.owner()));
    }

    @Test
    public void theDefaultBalanceValueOfaBankAccountMustBeZero() {
        BankAccount bankAccount = BankAccount
                .createObject()
                .withOwner(getOwnerName())
                .withBankAgency(getBankAgency())
                .withCode(getAccountCode())
                .withLimit(getAccountLimit())
                .build();

        assertThat(0.0, is(bankAccount.balance()));
    }

    // balance

    private double getAccountBalance() {
        return 100.0;
    }

    @Test
    public void aBankAccountCanHaveABalance() {
        BankAccount bankAccount = BankAccount
                .createObject()
                .withOwner(getOwnerName())
                .withBalance(getAccountBalance())
                .withBankAgency(getBankAgency())
                .withCode(getAccountCode())
                .withLimit(getAccountLimit())
                .build();

        assertThat(getOwnerName(), is(bankAccount.owner()));
        assertThat(getAccountBalance(), is(bankAccount.balance()));
    }

    // bankAgency

    private String getBankName() {
        return "Boston";
    }

    private int getBankCode() {
        return 33;
    }

    private Bank getBank() {
        return new Bank(getBankName(), getBankCode());
    }

    private int getBankAgencyCode() {
        return 1234;
    }

    private int getBankAgencyDigit() {
        return 0;
    }

    private BankAgency getBankAgency() {
        return new BankAgency(getBank(), getBankAgencyCode());
    }

    private BankAgency getBankAgencyWithDigit() {
        return new BankAgency(getBank(), getBankAgencyCode(), getBankAgencyDigit());
    }

    @Test(expected = MissingBankAgencyException.class)
    public void errorWhenABankAccountDoesNotHaveABankAgency() {
        BankAccount
                .createObject()
                .withOwner(getOwnerName())
                .build();
    }

    @Test
    public void aBankAccountMustHaveABankAgency() {
        BankAccount bankAccount = BankAccount
                .createObject()
                .withOwner(getOwnerName())
                .withBankAgency(getBankAgency())
                .withCode(getAccountCode())
                .withLimit(getAccountLimit())
                .build();

        assertThat(getBankAgencyCode(), is(bankAccount.agencyCode()));
        assertThat(bankAccount.agencyDigit(), nullValue());
    }

    @Test
    public void aBankAccountMustHaveABankAgencyAndCanHaveADigit() {
        BankAccount bankAccount = BankAccount
                .createObject()
                .withOwner(getOwnerName())
                .withBalance(getAccountBalance())
                .withBankAgency(getBankAgencyWithDigit())
                .withCode(getAccountCode())
                .withLimit(getAccountLimit())
                .build();

        assertThat(getBankAgencyCode(), is(bankAccount.agencyCode()));
        assertThat(getBankAgencyDigit(), is(bankAccount.agencyDigit()));
    }

    // code

    private int getAccountCode() {
        return 9876;
    }

    @Test(expected = MissingAccountCodeException.class)
    public void errorWhenABankAccountDoesNotHaveACode() {
        BankAccount
                .createObject()
                .withOwner(getOwnerName())
                .withBankAgency(getBankAgency())
                .build();
    }

    @Test
    public void aBankAccountMustHaveACode() {
        BankAccount bankAccount = BankAccount
                .createObject()
                .withOwner(getOwnerName())
                .withBankAgency(getBankAgency())
                .withCode(getAccountCode())
                .withLimit(getAccountLimit())
                .build();

        assertThat(getAccountCode(), is(bankAccount.code()));
    }

    // codeDigit
    private int getAccountCodeDigit() {
        return 1;
    }

    @Test
    public void aBankAccountMustHaveACodeAndCanHaveADigit() {
        BankAccount bankAccount = BankAccount
                .createObject()
                .withOwner(getOwnerName())
                .withBalance(getAccountBalance())
                .withBankAgency(getBankAgencyWithDigit())
                .withCode(getAccountCode())
                .withCodeDigit(getAccountCodeDigit())
                .withLimit(getAccountLimit())
                .build();

        assertThat(getAccountCodeDigit(), is(bankAccount.codeDigit()));
    }

    // limit

    private Double getAccountLimit() {
        return 1000.0;
    }

    @Test(expected = MissingAccountLimitException.class)
    public void errorWhenABankAccountDoesNotHaveALimit() {
        BankAccount
                .createObject()
                .withOwner(getOwnerName())
                .withBalance(getAccountBalance())
                .withBankAgency(getBankAgencyWithDigit())
                .withCode(getAccountCode())
                .withCodeDigit(getAccountCodeDigit())
                .build();
    }

    @Test
    public void aBankAccountMustHaveALimit() {
        BankAccount bankAccount = BankAccount
                .createObject()
                .withOwner(getOwnerName())
                .withBankAgency(getBankAgency())
                .withCode(getAccountCode())
                .withLimit(getAccountLimit())
                .build();

        assertThat(getAccountLimit(), is(bankAccount.limit()));
    }

    @Test(expected = EmptyWalletException.class)
    public void canNotWithdrawWithoutBalanceAndLimit() {
        BankAccount bankAccount = BankAccount
                .createObject()
                .withOwner(getOwnerName())
                .withBankAgency(getBankAgency())
                .withCode(getAccountCode())
                .withLimit(0.0)
                .build();

        bankAccount.withdraw(10.0);
    }

    @Test(expected = WithdrawBiggerThanTotalLimitException.class)
    public void canNotWithdrawWithoutBalance() {
        BankAccount bankAccount = BankAccount
                .createObject()
                .withOwner(getOwnerName())
                .withBalance(100.0)
                .withBankAgency(getBankAgency())
                .withCode(getAccountCode())
                .withLimit(0.0)
                .build();

        bankAccount.withdraw(110.0);
    }

    @Test(expected = WithdrawBiggerThanTotalLimitException.class)
    public void canNotWithdrawWithoutBalanceAndWithLowerLimit() {
        BankAccount bankAccount = BankAccount
                .createObject()
                .withOwner(getOwnerName())
                .withBalance(100.0)
                .withBankAgency(getBankAgency())
                .withCode(getAccountCode())
                .withLimit(getAccountLimit())
                .build();

        bankAccount.withdraw(1200.0);
    }

    @Test
    public void withdrawWithoutLimit() {
        BankAccount bankAccount = BankAccount
                .createObject()
                .withOwner(getOwnerName())
                .withBalance(100.0)
                .withBankAgency(getBankAgency())
                .withCode(getAccountCode())
                .withLimit(0.0)
                .build();

        BankAccount bankAccount2 = bankAccount.withdraw(100.0);
        assertThat(100.0, is(bankAccount.balance()));
        assertThat(0.0, is(bankAccount2.balance()));
    }

    @Test
    public void withdrawUsingLimit() {
        BankAccount bankAccount = BankAccount
                .createObject()
                .withOwner(getOwnerName())
                .withBalance(100.0)
                .withBankAgency(getBankAgency())
                .withCode(getAccountCode())
                .withLimit(100.0)
                .build();

        BankAccount bankAccount2 = bankAccount.withdraw(200.0);
        assertThat(100.0, is(bankAccount.balance()));
        assertThat(-100.0, is(bankAccount2.balance()));
        assertThat(0.0, is(bankAccount2.totalBalance()));
    }
}
