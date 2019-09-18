package com.accounting.personal.ontrack.asset;

import com.accounting.personal.ontrack.asset.exception.MissingNameException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BankTest {

    private String getName() {
        return "Boston";
    }

    private String getCode() {
        return "001";
    }

    @Test(expected = MissingNameException.class)
    public void errorWhenABankDoesNotHaveAName() {
        new Bank(getCode(), null);
    }

    @Test
    public void aBankMustHaveANameAndCode() {
        Bank bank = new Bank(getCode(), getName());

        assertThat(getName(), is(bank.getName()));
        assertThat(getCode(), is(bank.getCode()));
    }
}