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
public class BankAccountTest {

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
                .withBankBranch(getBankBranch())
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
                .withBankBranch(getBankBranch())
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
                .withBankBranch(getBankBranch())
                .withCode(getAccountCode())
                .withLimit(getAccountLimit())
                .build();

        assertThat(getOwnerName(), is(bankAccount.owner()));
        assertThat(getAccountBalance(), is(bankAccount.balance()));
    }

    // bankBranch

    private String getBankName() {
        return "Boston";
    }

    private int getBankCode() {
        return 33;
    }

    private Bank getBank() {
        return new Bank(getBankName(), getBankCode());
    }

    private int getBankBranchCode() {
        return 1234;
    }

    private int getBankBranchDigit() {
        return 0;
    }

    private BankBranch getBankBranch() {
        return new BankBranch(getBank(), getBankBranchCode());
    }

    private BankBranch getBankBranchWithDigit() {
        return new BankBranch(getBank(), getBankBranchCode(), getBankBranchDigit());
    }

    @Test(expected = MissingBankBranchException.class)
    public void errorWhenABankAccountDoesNotHaveABankBranch() {
        BankAccount
                .createObject()
                .withOwner(getOwnerName())
                .build();
    }

    @Test
    public void aBankAccountMustHaveABankBranch() {
        BankAccount bankAccount = BankAccount
                .createObject()
                .withOwner(getOwnerName())
                .withBankBranch(getBankBranch())
                .withCode(getAccountCode())
                .withLimit(getAccountLimit())
                .build();

        assertThat(getBankBranchCode(), is(bankAccount.branchCode()));
        assertThat(bankAccount.branchDigit(), nullValue());
    }

    @Test
    public void aBankAccountMustHaveABankBranchAndCanHaveADigit() {
        BankAccount bankAccount = BankAccount
                .createObject()
                .withOwner(getOwnerName())
                .withBalance(getAccountBalance())
                .withBankBranch(getBankBranchWithDigit())
                .withCode(getAccountCode())
                .withLimit(getAccountLimit())
                .build();

        assertThat(getBankBranchCode(), is(bankAccount.branchCode()));
        assertThat(getBankBranchDigit(), is(bankAccount.branchDigit()));
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
                .withBankBranch(getBankBranch())
                .build();
    }

    @Test
    public void aBankAccountMustHaveACode() {
        BankAccount bankAccount = BankAccount
                .createObject()
                .withOwner(getOwnerName())
                .withBankBranch(getBankBranch())
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
                .withBankBranch(getBankBranchWithDigit())
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
                .withBankBranch(getBankBranchWithDigit())
                .withCode(getAccountCode())
                .withCodeDigit(getAccountCodeDigit())
                .build();
    }

    @Test
    public void aBankAccountMustHaveALimit() {
        BankAccount bankAccount = BankAccount
                .createObject()
                .withOwner(getOwnerName())
                .withBankBranch(getBankBranch())
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
                .withBankBranch(getBankBranch())
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
                .withBankBranch(getBankBranch())
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
                .withBankBranch(getBankBranch())
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
                .withBankBranch(getBankBranch())
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
                .withBankBranch(getBankBranch())
                .withCode(getAccountCode())
                .withLimit(100.0)
                .build();

        BankAccount bankAccount2 = bankAccount.withdraw(200.0);
        assertThat(100.0, is(bankAccount.balance()));
        assertThat(-100.0, is(bankAccount2.balance()));
        assertThat(0.0, is(bankAccount2.totalBalance()));
    }
}
