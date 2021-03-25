package com.vm.demo.domain;

import com.vm.demo.boundary.Employee;
import com.vm.demo.boundary.helper.EmployeeFileHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class EmployeeService {

    public void saveFile(MultipartFile file) {
        try {
            List<Employee> e = EmployeeFileHelper.convertCsvToEmployee(file.getInputStream());
            for (Employee emp: e) {
                System.out.println("emp name: "+emp.toString());
            }
        }catch (IOException io) {
            throw new RuntimeException("Failed to save CSv data : "+io.getMessage());
        }
    }
}
