package org.employees.report.utility.factory;

import org.employees.report.utility.entity.BaseEmployee;
import org.employees.report.utility.entity.Employee;

import java.math.BigDecimal;


public class EmployeeFactory implements BaseEmployeeFactory {
    @Override
    public BaseEmployee createEmployee(String position, String id, String name, BigDecimal salary, String managerId) {
        return new Employee(position, id, name, salary, managerId);
    }


}
