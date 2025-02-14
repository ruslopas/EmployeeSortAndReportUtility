package org.employees.report.utility.entity;

import java.math.BigDecimal;

public abstract class AbstractEmployee implements BaseEmployee {
    String position;
    String id;
    String name;
    BigDecimal salary;

    public AbstractEmployee(String position, String id, String name, BigDecimal salary) {
        this.position = position;
        this.id = id;
        this.name = name;
        this.salary = salary;

    }

    @Override
    public String getPosition() {
        return position;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public BigDecimal getSalary() {
        return salary;
    }

    @Override
    public String dataToString() {
        return getPosition() + "," + getId() + "," + getName() + "," + getSalary();
    }

}
