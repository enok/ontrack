package com.accounting.personal.ontrack.asset;

import com.accounting.personal.ontrack.asset.exception.MissingNameException;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.springframework.util.StringUtils;

public class Bank {
    private final String name;
    private final String code;

    public Bank(String code, String name) {
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

    public String getCode() {
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
