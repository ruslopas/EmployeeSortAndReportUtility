package org.employees.report.utility;

import org.employees.report.utility.cli.CommandLineOptions;
import picocli.CommandLine;

public class Main {
    public static void main(String[] args) {
        int exitCode = new CommandLine(new CommandLineOptions()).execute(args);
        System.exit(exitCode);
    }
}
