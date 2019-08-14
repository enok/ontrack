package com.accounting.personal.ontrack.bill;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionTest {

    @Test
    public void aTransactionMustHaveAType_Money() {
        Transaction transaction = new Transaction(TransactionType.MONEY);

        assertThat(TransactionType.MONEY, is(transaction.getType()));
    }
}
