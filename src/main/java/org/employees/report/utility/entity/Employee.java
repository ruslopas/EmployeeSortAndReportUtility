package org.employees.report.utility.entity;

import java.math.BigDecimal;

public class Employee extends AbstractEmployee {
    String managerId;

    public Employee(String position, String id, String name, BigDecimal salary, String managerId) {
        super(position, id, name, salary);
        this.managerId = managerId;
    }

    @Override
    public String getManagerId() {
        return managerId;
    }

    @Override
    public String getDepartment() {
        return managerId;
    }


}
