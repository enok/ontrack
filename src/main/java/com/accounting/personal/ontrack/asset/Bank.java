package com.accounting.personal.ontrack.asset;

// TODO create tests
public class Bank {
    private final String name;
    private final int code;

    public Bank(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }
}
