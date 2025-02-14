package org.employees.report.utility.command;

import org.employees.report.utility.service.EmployeeService;

public class SortCommand implements Command {
    private final EmployeeService employeeService;
    private final String sortBy;
    private final String order;

    public SortCommand(EmployeeService employeeService, String sortBy, String order) {
        this.employeeService = employeeService;
        this.sortBy = sortBy;
        this.order = order;
    }

    @Override
    public void execute() {
        employeeService.sortEmployees(sortBy, order);
    }
}
