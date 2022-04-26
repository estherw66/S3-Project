package com.fontys.s3itproject.business.impl;

import com.fontys.s3itproject.business.EmployeeService;
import com.fontys.s3itproject.entity.Employee;
import com.fontys.s3itproject.persistence.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepo;

    @Override
    public boolean createEmployee(Employee employee) {
        return employeeRepo.createEmployee(employee);
    }

    @Override
    public List<Employee> readEmployees() {
        return employeeRepo.readEmployees();
    }

    @Override
    public Employee readEmployeeByID(Long id) {
        return employeeRepo.readEmployeeByID(id);
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        return employeeRepo.updateEmployee(employee);
    }

    @Override
    public boolean deleteEmployee(Employee employee) {
        return employeeRepo.deleteEmployee(employee);
    }

}
