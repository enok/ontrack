package com.accounting.personal.ontrack.bill;

import java.util.ArrayList;
import java.util.List;

public class ExpenseGroup {
    private final String name;
    private final List<ExpenseSubGroup> expenseSubGroups;

    public ExpenseGroup(final String name) {
        this.name = name;
        expenseSubGroups = new ArrayList<>();
    }
}
