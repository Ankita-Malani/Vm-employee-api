package com.vm.demo.service;

import com.vm.demo.boundary.Employee;
import com.vm.demo.boundary.helper.EmployeeFileHelper;
import com.vm.demo.jpa.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public void saveFile(MultipartFile file) {
        try {
            List<Employee> e = EmployeeFileHelper.convertCsvToEmployee(file.getInputStream());
            try{
                employeeRepository.saveAll(e);
                Iterable<Employee> e1  = employeeRepository.findAll();
                for (Employee e2: e1) {
                    System.out.println("emp name: "+e2.toString());
                }
            } catch (Exception exp) {
                exp.printStackTrace();
            }


        }catch (IOException io) {
            throw new RuntimeException("Failed to save CSv data : "+io.getMessage());
        }
    }
}
