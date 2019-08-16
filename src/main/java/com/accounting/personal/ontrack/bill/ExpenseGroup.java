package com.accounting.personal.ontrack.bill;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExpenseGroup {
    private final String name;
    private final List<ExpenseSubGroup> expenseSubGroups;

    public ExpenseGroup(final String name, final ExpenseSubGroup expenseSubGroup) {
        this.name = name;
        expenseSubGroups = new ArrayList<>();
        expenseSubGroups.add(expenseSubGroup);
    }

    public String getName() {
        return name;
    }

    public List<String> getSubGroupNames() {
        return expenseSubGroups
                .stream()
                .map(ExpenseSubGroup::getName)
                .collect(Collectors.toList());
    }
}
