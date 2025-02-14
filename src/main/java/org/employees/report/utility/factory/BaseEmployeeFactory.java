package org.employees.report.utility.factory;

import org.employees.report.utility.entity.BaseEmployee;

import java.math.BigDecimal;

public interface BaseEmployeeFactory {
    BaseEmployee createEmployee(String position, String id, String name, BigDecimal salary, String department);

}
