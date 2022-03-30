package com.fontys.S3ITProject.business.Impl;

import com.fontys.S3ITProject.business.EmployeeService;
import com.fontys.S3ITProject.models.Employee;
import com.fontys.S3ITProject.persistence.EmployeeRepository;
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
        return false;
    }

    @Override
    public List<Employee> readEmployees() {
        return null;
    }

    @Override
    public Employee readEmployeeByID(int id) {
        return null;
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        return false;
    }

    @Override
    public boolean deleteEmployee(int employeeID) {
        return false;
    }
}
