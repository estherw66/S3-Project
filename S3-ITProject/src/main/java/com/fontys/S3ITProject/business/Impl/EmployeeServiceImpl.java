package com.fontys.s3itproject.business.impl;

import com.fontys.s3itproject.business.EmployeeService;
import com.fontys.s3itproject.models.Employee;
import com.fontys.s3itproject.persistence.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepo;

    public EmployeeServiceImpl(EmployeeRepository employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public boolean createEmployee(Employee employee) {
        return employeeRepo.createEmployee(employee);
    }

    @Override
    public List<Employee> readEmployees() {
        return employeeRepo.readEmployees();
    }

    @Override
    public Employee readEmployeeByID(int id) {
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
