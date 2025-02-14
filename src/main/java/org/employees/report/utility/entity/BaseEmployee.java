package org.employees.report.utility.entity;

import java.math.BigDecimal;

public interface BaseEmployee {
    String dataToString();

    String getPosition();

    String getDepartment();

    String getId();

    String getManagerId();

    String getName();

    BigDecimal getSalary();
}

