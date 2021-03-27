package com.vm.demo.service;

import com.vm.demo.boundary.Employee;
import com.vm.demo.jpa.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public void saveFile(List<Employee> e) {
            try{
                employeeRepository.saveAll(e);
            } catch (Exception exp) {
                exp.printStackTrace();
            }
    }

    public void deleteEmployee(int employeeId) {
        try{
            employeeRepository.deleteById(employeeId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateEmployee(Employee e) {
        try {
            employeeRepository.save(e);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
