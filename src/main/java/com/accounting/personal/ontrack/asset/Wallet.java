package com.accounting.personal.ontrack.asset;

import com.accounting.personal.ontrack.asset.exception.EmptyWalletException;
import com.accounting.personal.ontrack.asset.exception.NameMustNotBeEmpty;
import com.accounting.personal.ontrack.asset.exception.NegativeValueDepositedException;
import com.accounting.personal.ontrack.asset.exception.NegativeValueWithdrawnException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class Wallet {
    private final String owner;
    private final double money;

    public Wallet() {
        this.owner = null;
        this.money = 0.0;
    }

    public Wallet(final String owner, final double money) {
        validateOwnerName(owner);
        this.owner = owner;
        this.money = money;
    }

    public final Wallet withdraw(final double money) {
        if (money < 0) {
            throw new NegativeValueWithdrawnException("Cannot withdraw a negative amount of money.");
        }
        if (this.money <= 0) {
            throw new EmptyWalletException("The wallet is empty.");
        }
        validateOwnerName(owner);
        return new Wallet(owner, this.money - money);
    }

    public final Wallet deposit(final double money) {
        if (money < 0) {
            throw new NegativeValueDepositedException("Cannot deposit a negative amount of money.");
        }
        validateOwnerName(owner);
        return new Wallet(owner, this.money + money);
    }

    public final String owner() {
        return owner;
    }

    public final double balance() {
        return money;
    }

    private void validateOwnerName(String owner) {
        if (StringUtils.isEmpty(owner)) {
            throw new NameMustNotBeEmpty("A wallet must have an owner name.");
        }
    }
}
