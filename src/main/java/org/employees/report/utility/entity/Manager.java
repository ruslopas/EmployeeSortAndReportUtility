package org.employees.report.utility.entity;

import java.math.BigDecimal;

public class Manager extends AbstractEmployee {
    String department;

    public Manager(String position, String id, String name, BigDecimal salary, String department) {
        super(position, id, name, salary);
        this.department = department;
    }

    @Override
    public String getDepartment() {
        return department;
    }

    @Override
    public String getManagerId() {
        return department;
    }

}
