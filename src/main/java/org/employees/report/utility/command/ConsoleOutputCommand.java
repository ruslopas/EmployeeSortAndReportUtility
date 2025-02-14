package org.employees.report.utility.command;

import org.employees.report.utility.service.EmployeeService;

import java.util.List;

public class ConsoleOutputCommand implements Command {
    EmployeeService employeeService;

    public ConsoleOutputCommand(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void execute() {
        List<String> data = employeeService.getFormattedData();
        data.forEach(System.out::println);
    }
}

