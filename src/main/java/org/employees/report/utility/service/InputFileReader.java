package org.employees.report.utility.service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InputFileReader {
    private List<String> inputEmployeeData;

    public InputFileReader(List<String> inputFiles) {
        readDataFromInputFile(inputFiles);
    }

    private void readDataFromInputFile(List<String> inputFileName) {
        inputEmployeeData = new ArrayList<>();

        for (String inputFile : inputFileName) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile))) {
                String line = bufferedReader.readLine();
                while (line != null) {
                    inputEmployeeData.add(line);
                    line = bufferedReader.readLine();
                }
            } catch (FileNotFoundException e) {
                //   throw new RuntimeException(e);
                System.out.println("File not found: " + inputFile);
                System.exit(0);
            } catch (IOException e) {
                // throw new RuntimeException(e);
                System.out.println("Error reading file: " + inputFile);
                System.exit(0);
            }
        }
    }

    public List<String> getInputEmployeeData() {
        return inputEmployeeData;
    }
}
