package org.employees.report.utility.factory;

import org.employees.report.utility.entity.BaseEmployee;
import org.employees.report.utility.entity.Manager;

import java.math.BigDecimal;

public class ManagerFactory implements BaseEmployeeFactory {

    @Override
    public BaseEmployee createEmployee(String position, String id, String name, BigDecimal salary, String department) {
        return new Manager(position, id, name, salary, department);
    }
}
