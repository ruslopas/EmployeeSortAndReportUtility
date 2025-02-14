package org.employees.report.utility.command;

import java.util.ArrayList;
import java.util.List;

public class CommandExecutor {
    private final List<Command> commands = new ArrayList<>();

    public void addCommand(Command command) {
        commands.add(command);
    }

    public void executeCommands() {
        for (Command command : commands) {
            command.execute();
        }
        commands.clear();  // Очищаем список команд после выполнения
    }
}
