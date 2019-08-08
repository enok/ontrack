package com.accounting.personal.ontrack.asset;

import com.accounting.personal.ontrack.asset.exception.MissingNameException;
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
}
