package com.vm.demo.boundary.helper;

import com.vm.demo.boundary.Employee;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class EmployeeFileHelper {

    public static String TYPE = "text/csv";

    private static final String EMPLOYEE_NAME = "name";
    private static final String EMPLOYEE_AGE = "age";

    public static boolean hasCSVFormat(MultipartFile file) {
        return (!TYPE.equals(file.getContentType())) ? false : true;
    }

    public static List<Employee> convertCsvToEmployee(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<Employee> employeeList = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Employee employee = new Employee(
                        csvRecord.get(EMPLOYEE_NAME),
                        Integer.parseInt(csvRecord.get(EMPLOYEE_AGE))
                );
                employeeList.add(employee);
            }
            return employeeList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

}
