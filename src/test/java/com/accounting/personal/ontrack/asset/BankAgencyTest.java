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
public class BankAgencyTest {

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
        new BankAgency(null, getBankAgencyCode());
    }

    @Test
    public void aBankAgencyMustHaveABank() {
        BankAgency bankAgency = new BankAgency(getBank(), getBankAgencyCode());

        assertThat(getBankName(), is(bankAgency.bankName()));
        assertThat(getBankCode(), is(bankAgency.bankCode()));
    }

    @Test
    public void aBankAgencyCanHaveADigit() {
        BankAgency bankAgency = new BankAgency(getBank(), getBankAgencyCode(), getBankAgencyDigit());

        assertThat(getBankName(), is(bankAgency.bankName()));
        assertThat(1, is(bankAgency.getDigit()));
    }
}