package com.accounting.personal.ontrack.bill;

public class ExpenseSubGroup {
    private final String name;
    private final ExpenseGroup group;

    public ExpenseSubGroup(final String name, final ExpenseGroup group) {
        this.name = name;
        this.group = group;
    }

    public String getName() {
        return name;
    }
}
