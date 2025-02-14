package org.employees.report.utility.command;

import org.employees.report.utility.service.EmployeeService;

public class FileOutputCommand implements Command {
    private final String filePath;
    private final EmployeeService employeeService;

    public FileOutputCommand(EmployeeService employeeService, String filePath) {
        this.employeeService = employeeService;
        this.filePath = filePath;
    }

    @Override
    public void execute() {
        employeeService.writeToFile(employeeService.getFormattedData(), filePath);
    }
}
