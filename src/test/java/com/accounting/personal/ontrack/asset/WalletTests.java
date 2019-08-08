package com.accounting.personal.ontrack.asset;

import com.accounting.personal.ontrack.asset.exception.EmptyWalletException;
import com.accounting.personal.ontrack.asset.exception.MissingNameException;
import com.accounting.personal.ontrack.asset.exception.NegativeValueDepositedException;
import com.accounting.personal.ontrack.asset.exception.NegativeValueWithdrawnException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WalletTests {

    @Test(expected = MissingNameException.class)
    public void errorWhenAWalletDoesNotHaveAnOwnerName() {
        new Wallet(null, 0.0);
    }

    @Test(expected = NegativeValueWithdrawnException.class)
    public void errorWhenWithdrawNegativeValueFromAWallet() {
        Wallet wallet = new Wallet("Bill", 0.0);
        wallet.withdraw(-1.0);
    }

    @Test(expected = EmptyWalletException.class)
    public void errorWhenWithdrawFromAnEmptyWallet() {
        Wallet wallet = new Wallet("Bill", 0.0);
        wallet.withdraw(10.0);
    }

    @Test
    public void aWalletMustHaveAnOwnerName() {
        Wallet wallet = new Wallet("Bill", 10.0);

        assertThat(wallet.owner(), is("Bill"));
    }

    @Test
    public void afterWithdrawTheObjectMustBeAnother() {
        Wallet wallet = new Wallet("Bill", 10.0);
        Wallet newWallet = wallet.withdraw(10.0);

        assertThat(wallet, sameInstance(wallet));
        assertThat(wallet, not(sameInstance(newWallet)));
    }

    @Test
    public void afterWithdrawTheOwnerMustRemainTheSame() {
        Wallet wallet = new Wallet("Bill", 10.0);
        Wallet newWallet = wallet.withdraw(10.0);

        assertThat(wallet.owner(), is(newWallet.owner()));
    }

    @Test
    public void afterWithdrawTheSameObjectDoesNotChange() {
        Wallet wallet = new Wallet("Bill", 10.0);
        wallet.withdraw(10.0);

        assertThat(10.0, is(wallet.balance()));
    }

    @Test
    public void afterWithdrawTheNewBalanceResidesInsideTheNewObject() {
        Wallet wallet = new Wallet("Bill", 10.0);
        Wallet newWallet = wallet.withdraw(10.0);

        assertThat(0.0, is(newWallet.balance()));
    }

    @Test(expected = NegativeValueDepositedException.class)
    public void errorWhenDepositingNegativeAmountIntoAWallet() {
        Wallet wallet = new Wallet("Bill", 0.0);
        wallet.deposit(-1.0);
    }

    @Test
    public void afterDepositTheObjectMustBeAnother() {
        Wallet wallet = new Wallet("Bill", 10.0);
        Wallet newWallet = wallet.deposit(10.0);

        assertThat(wallet, sameInstance(wallet));
        assertThat(wallet, not(sameInstance(newWallet)));
    }

    @Test
    public void afterDepositTheOwnerMustRemainTheSame() {
        Wallet wallet = new Wallet("Bill", 10.0);
        Wallet newWallet = wallet.deposit(10.0);

        assertThat(wallet.owner(), is(newWallet.owner()));
    }

    @Test
    public void afterDepositTheSameObjectDoesNotChange() {
        Wallet wallet = new Wallet("Bill", 10.0);
        wallet.deposit(10.0);

        assertThat(10.0, is(wallet.balance()));
    }

    @Test
    public void afterDepositTheNewBalanceResidesInsideTheNewObject() {
        Wallet wallet = new Wallet("Bill", 10.0);
        Wallet newWallet = wallet.deposit(10.0);

        assertThat(20.0, is(newWallet.balance()));
    }
}
