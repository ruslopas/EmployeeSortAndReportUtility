package org.employees.report.utility.service;

import org.employees.report.utility.entity.BaseEmployee;
import org.employees.report.utility.factory.BaseEmployeeFactory;
import org.employees.report.utility.factory.EmployeeFactory;
import org.employees.report.utility.factory.ManagerFactory;

import java.math.BigDecimal;
import java.util.*;


public class DataService {
    private final Map<String, List<BaseEmployee>> employees = new TreeMap<>();
    private final List<BaseEmployee> allLoadedEmployees = new ArrayList<>();
    private final List<String> invalidData = new ArrayList<>();
    private final List<String> allInputEmployees;

    public DataService(InputFileReader inputFileReader) {
        this.allInputEmployees = inputFileReader.getInputEmployeeData();
        createDataBase(allInputEmployees);
    }

    private void createDataBase(List<String> allInputEmployees) {

        for (String line : allInputEmployees) {
            List<String> employeeData = parseEmployeeData(line);
            if (employeeData.size() != 5) {
                invalidData.add(line);
                continue;
            }

            if (!isValidEmployeeData(employeeData)) {
                invalidData.add(line);
                continue;
            }

            BaseEmployee employee = createEmployee(employeeData);
            allLoadedEmployees.add(employee);
        }
        employees.putAll(setDepartmentDataMap(allLoadedEmployees));
    }

    private List<String> parseEmployeeData(String line) {
        return Arrays.stream(line.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toList();
    }

    private boolean isValidEmployeeData(List<String> employeeData) {
        String position = employeeData.get(0);
        String id = employeeData.get(1);
        String salary = employeeData.get(3);

        return isValidPosition(position) && isValidId(id) && isValidSalary(salary);
    }

    private boolean isValidPosition(String position) {
        return position.equals("Manager") || position.equals("Employee");
    }

    private boolean isValidId(String id) {
        try {
            int verificationId = Integer.parseInt(id);
            return verificationId > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidSalary(String salary) {
        try {
            double verificationSalary = Double.parseDouble(salary);
            return verificationSalary > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private BaseEmployee createEmployee(List<String> employeeData) {
        String position = employeeData.get(0);
        String id = employeeData.get(1);
        String name = employeeData.get(2);
        BigDecimal salary = BigDecimal.valueOf(Double.parseDouble(employeeData.get(3)));
        String department = employeeData.get(4);

        return createEmployeeByPosition(position, id, name, salary, department);
    }

    private BaseEmployee createEmployeeByPosition(String position,
                                                  String id,
                                                  String name,
                                                  BigDecimal salary,
                                                  String department) {
        BaseEmployeeFactory personFactory = position.equals("Employee")
                ? new EmployeeFactory()
                : new ManagerFactory();
        return personFactory.createEmployee(position, id, name, salary, department);
    }

    private Map<String, List<BaseEmployee>> setDepartmentDataMap(List<BaseEmployee> allEmployees) {
        Map<String, List<BaseEmployee>> departmentDataMap = new TreeMap<>();

        // Группируем менеджеров по отделам
        allEmployees.stream()
                .filter(emp -> emp.getPosition().equals("Manager"))
                .forEach(manager -> departmentDataMap
                        .computeIfAbsent(manager.getDepartment(), k -> new ArrayList<>()) // Создаём отдел, если его нет
                        .add(manager)); // Добавляем менеджера в отдел

        // Добавляем сотрудников в соответствующие отделы
        allEmployees.stream()
                .filter(emp -> emp.getPosition().equals("Employee"))
                .forEach(employee -> allEmployees.stream()
                        .filter(manager -> manager.getPosition().equals("Manager")
                                && manager.getId().equals(employee.getManagerId()))
                        .findFirst()
                        .ifPresent(manager -> departmentDataMap.get(manager.getDepartment()).add(employee)));

        return departmentDataMap;
    }

    public List<String> getInvalidData() {
        return invalidData;
    }

    public Map<String, List<BaseEmployee>> getDepartmentDataMap() {
        return employees;
    }

    public List<String> getAllInputEmployees() {
        return allInputEmployees;
    }
}