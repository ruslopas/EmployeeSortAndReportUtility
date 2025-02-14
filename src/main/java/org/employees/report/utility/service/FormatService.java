package org.employees.report.utility.service;

import org.employees.report.utility.entity.BaseEmployee;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

//Форматирует отчет перед отправкой в консоль или файл

public class FormatService {

    public List<String> format(Map<String, List<BaseEmployee>> employees,
                               List<String> invalidData,
                               List<String> allInputEmployees,
                               String sortBy,
                               String sortOrder) {
        List<String> result = new ArrayList<>();

        if (sortBy.equals("default")) {
            return allInputEmployees;
        }

        Comparator<BaseEmployee> comparator = sortBy.equals("salary")
                ? Comparator.comparing(BaseEmployee::getSalary)
                : Comparator.comparing(BaseEmployee::getName);

        if (sortOrder.equals("desc")) {
            comparator = comparator.reversed();
        }

        for (Map.Entry<String, List<BaseEmployee>> entry : employees.entrySet()) {
            String department = entry.getKey();
            List<BaseEmployee> departmentEmployees = entry.getValue();

            result.add(department);

            List<BaseEmployee> employeesToSort = filterNonManagers(departmentEmployees);
            employeesToSort.sort(comparator);

            departmentEmployees.stream()
                    .filter(emp -> emp.getPosition().equals("Manager"))
                    .forEach(emp -> result.add(emp.dataToString()));

            employeesToSort.forEach(emp -> result.add(emp.dataToString()));

            if (!departmentEmployees.isEmpty()) {
                BigDecimal averageSalary = calculateAverageSalary(departmentEmployees);
                result.add(String.format("%d,%s", departmentEmployees.size(), averageSalary));
            }
        }
        result.addAll(formatInvalidData(invalidData));
        return result;
    }

    private List<BaseEmployee> filterNonManagers(List<BaseEmployee> employees) {
        return employees.stream()
                .filter(emp -> !emp.getPosition().equals("Manager"))
                .collect(Collectors.toList());
    }

    private BigDecimal calculateAverageSalary(List<BaseEmployee> employees) {
        return employees.stream()
                .map(BaseEmployee::getSalary)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(new BigDecimal(employees.size()), 2, RoundingMode.HALF_UP);
    }

    private List<String> formatInvalidData(List<String> invalidData) {
        List<String> result = new ArrayList<>();
        result.add("Некорректные данные :");
        result.addAll(invalidData);
        return result;
    }
}
