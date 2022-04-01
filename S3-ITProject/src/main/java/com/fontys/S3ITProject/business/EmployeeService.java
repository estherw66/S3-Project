package com.fontys.S3ITProject.business;

import com.fontys.S3ITProject.models.Employee;

import java.util.List;

public interface EmployeeService {
    public boolean createEmployee(Employee employee);
    public List<Employee> readEmployees();
    public Employee readEmployeeByID(int id);
    public boolean updateEmployee(Employee employee);
    public boolean deleteEmployee(Employee employee);
}
