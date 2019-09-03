package com.accounting.personal.ontrack.asset;

import com.accounting.personal.ontrack.asset.exception.MissingBankException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BankBranchTest {

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
        return 1;
    }

    @Test(expected = MissingBankException.class)
    public void errorWhenABankBranchDoesNotHaveABank() {
        new BankBranch(null, getBankBranchCode());
    }

    @Test
    public void aBankBranchMustHaveABank() {
        BankBranch bankBranch = new BankBranch(getBank(), getBankBranchCode());

        assertThat(getBankName(), is(bankBranch.bankName()));
        assertThat(getBankCode(), is(bankBranch.bankCode()));
    }

    @Test
    public void aBankBranchCanHaveADigit() {
        BankBranch bankBranch = new BankBranch(getBank(), getBankBranchCode(), getBankBranchDigit());

        assertThat(getBankName(), is(bankBranch.bankName()));
        assertThat(1, is(bankBranch.getDigit()));
    }
}