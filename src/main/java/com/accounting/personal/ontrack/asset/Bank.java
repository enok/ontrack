package com.accounting.personal.ontrack.asset;

import com.accounting.personal.ontrack.asset.exception.MissingNameException;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.util.StringUtils;

public class Bank {
    private final String name;
    private final int code;

    public Bank(String name, int code) {
        validateName(name);
        this.name = name;
        this.code = code;
    }

    private void validateName(String owner) {
        if (StringUtils.isEmpty(owner)) {
            throw new MissingNameException("A bank must have a name.");
        }
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Bank bank = (Bank) o;

        return new EqualsBuilder()
                .append(code, bank.code)
                .append(name, bank.name)
                .isEquals();
    }
}
