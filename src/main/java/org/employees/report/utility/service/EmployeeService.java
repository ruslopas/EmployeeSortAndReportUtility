package org.employees.report.utility.service;

import org.employees.report.utility.entity.BaseEmployee;

import java.util.*;

public class EmployeeService {

    private final Map<String, List<BaseEmployee>> employees;
    private final List<String> invalidData;
    private final List<String> allInputEmployees;
    private List<String> formattedData;

    public EmployeeService(List<String> inputFiles) {
        InputFileReader fileReader = new InputFileReader(inputFiles);
        DataService dataService = new DataService(fileReader);
        invalidData = dataService.getInvalidData();
        employees = dataService.getDepartmentDataMap();
        allInputEmployees = dataService.getAllInputEmployees();
        formattedData = new ArrayList<>();
    }


    public void sortEmployees(String sortBy, String order) {
        FormatService formatService = new FormatService();
        setFormattedData(formatService.format(employees, invalidData, allInputEmployees, sortBy, order));
    }

    public List<String> getFormattedData() {
        return formattedData;
    }

    private void setFormattedData(List<String> formattedData) {
        this.formattedData = formattedData;
    }

    public void writeToFile(List<String> formattedData, String fileName) {
        OutputFileWriter outputFileWriter = new OutputFileWriter();
        outputFileWriter.writeToFile(formattedData, fileName);
    }
}

