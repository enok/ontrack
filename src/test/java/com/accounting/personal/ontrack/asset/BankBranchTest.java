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

    private int getBankAgencyCode() {
        return 1234;
    }

    private int getBankAgencyDigit() {
        return 1;
    }

    @Test(expected = MissingBankException.class)
    public void errorWhenABankAgencyDoesNotHaveABank() {
        new BankBranch(null, getBankAgencyCode());
    }

    @Test
    public void aBankAgencyMustHaveABank() {
        BankBranch bankBranch = new BankBranch(getBank(), getBankAgencyCode());

        assertThat(getBankName(), is(bankBranch.bankName()));
        assertThat(getBankCode(), is(bankBranch.bankCode()));
    }

    @Test
    public void aBankAgencyCanHaveADigit() {
        BankBranch bankBranch = new BankBranch(getBank(), getBankAgencyCode(), getBankAgencyDigit());

        assertThat(getBankName(), is(bankBranch.bankName()));
        assertThat(1, is(bankBranch.getDigit()));
    }
}