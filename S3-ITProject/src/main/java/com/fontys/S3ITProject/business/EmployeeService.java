package com.fontys.s3itproject.business;

import com.fontys.s3itproject.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EmployeeService {
    public boolean createEmployee(Employee employee);
    public List<Employee> readEmployees();
    public Employee readEmployeeByID(Long id);
    public boolean updateEmployee(Employee employee);
    public boolean deleteEmployee(Employee employee);
}
