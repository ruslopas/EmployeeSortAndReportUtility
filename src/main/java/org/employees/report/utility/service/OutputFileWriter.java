package org.employees.report.utility.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class OutputFileWriter {

    protected void writeToFile(List<String> formattedData, String outputFile) {
        if (!formattedData.isEmpty()) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile))) {
                for (String str : formattedData) {
                    bufferedWriter.append(str);
                    bufferedWriter.newLine();
                }
            } catch (IOException e) {
                System.out.println("Permission denied\nNo such file or directory :" + outputFile);
                System.exit(0);
            }
        }
        System.out.println("File was successfully created at " + outputFile);
    }
}

