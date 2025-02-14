package org.employees.report.utility.cli;

import org.employees.report.utility.command.ConsoleOutputCommand;
import org.employees.report.utility.command.FileOutputCommand;
import org.employees.report.utility.service.EmployeeService;
import org.employees.report.utility.command.CommandExecutor;
import org.employees.report.utility.command.SortCommand;
import picocli.CommandLine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "Employee Report Utility",
        mixinStandardHelpOptions = true,
        description = "Программа для генерации отчета по сотрудникам."
)
public class CommandLineOptions implements Callable<Integer> {

    @CommandLine.Option(names = {"-s", "--sort"}, defaultValue = "default", description = "Сортировка: name или salary")
    private String sortBy;

    @CommandLine.Option(
            names = {"-o"},
            description = "Можно передать два значения: одно для порядка сортировки (asc/desc), второе для вывода (console/file)",
            split = ","
    )
    private final List<String> orderAndOutputOptions = new ArrayList<>();

    @CommandLine.Option(names = {"--order"}, description = "Порядок сортировки: asc или desc")
    private String order;

    @CommandLine.Option(names = {"--output"}, defaultValue = "console", description = "Вывод: console или file")
    private String outputType;

    @CommandLine.Option(names = {"-p", "--path"}, description = "Путь к файлу (если --output=file)")
    private String outputFilePath;

    @CommandLine.Parameters(description = "Файлы с данными")
    private List<String> inputFiles;

    @Override
    public Integer call() {
        if (inputFiles == null || inputFiles.isEmpty()) {
            System.out.println("Ошибка: Не указаны входные файлы.\n");
            printHelp();
            return 0;
        }

        // Обрабатываем значения, переданные через `-o`
        for (String option : orderAndOutputOptions) {
            if (option.equalsIgnoreCase("asc") || option.equalsIgnoreCase("desc")) {
                if (order != null) {
                    System.out.println("Ошибка: Указано более одного значения сортировки (-o=asc и --order=desc одновременно).");
                    printHelp();
                    return 0;
                }
                order = option;
            } else if (option.equalsIgnoreCase("console") || option.equalsIgnoreCase("file")) {
                if (outputType != null && !outputType.equals("console")) {
                    System.out.println("Ошибка: Указано более одного типа вывода (-o=console и --output=file одновременно).");
                    printHelp();
                    return 0;
                }
                outputType = option;
            } else {
                System.out.println("Ошибка: Неверное значение для -o: " + option);
                printHelp();
                return 0;
            }
        }

        // Если параметры `--order` или `--output` тоже указаны, они не должны конфликтовать с `-o`
        if (order != null && orderAndOutputOptions.contains("asc") && orderAndOutputOptions.contains("desc")) {
            System.out.println("Ошибка: Передано два противоречивых значения порядка сортировки.");
            printHelp();
            return 0;
        }

        if (outputType != null && orderAndOutputOptions.contains("console") && orderAndOutputOptions.contains("file")) {
            System.out.println("Ошибка: Передано два противоречивых типа вывода.");
            printHelp();
            return 0;
        }

        // Проверка параметров
        if (sortBy != null && !sortBy.equals("name") && !sortBy.equals("salary") && !sortBy.equals("default")) {
            System.out.println("Ошибка: Неверный параметр сортировки.");
            printHelp();
            return 0;
        }
        if (sortBy != null && !sortBy.equals("default") && order == null) {
            System.out.println("Ошибка: Если указан --sort требуется указать --order ");
            printHelp();
            return 0;
        }

        if (order != null && !order.equals("asc") && !order.equals("desc")) {
            System.out.println("Ошибка: Неверный порядок сортировки.");
            printHelp();
            return 0;
        }

        if (outputType != null && !outputType.equals("console") && !outputType.equals("file")) {
            System.out.println("Ошибка: Неверный тип вывода.");
            printHelp();
            return 0;
        }

        if ("file".equals(outputType) && outputFilePath == null) {
            System.out.println("Ошибка: если --output=file, нужно указать --path=path/to/file.");
            printHelp();
            return 0;
        }

        // Создаем командный процессор
        CommandExecutor executor = new CommandExecutor();
        EmployeeService employeeService = new EmployeeService(inputFiles);

        // Добавляем команду сортировки
        if (sortBy != null) {
            executor.addCommand(new SortCommand(employeeService, sortBy, order));
        }

        // Выбираем команду вывода
        if ("file".equals(outputType)) {
            executor.addCommand(new FileOutputCommand(employeeService, outputFilePath));
        } else {
            executor.addCommand(new ConsoleOutputCommand(employeeService));
        }

        // Запускаем выполнение команд
        executor.executeCommands();
        return 0;
    }

    private void printHelp() {
        new CommandLine(this).usage(System.err);
    }
}


