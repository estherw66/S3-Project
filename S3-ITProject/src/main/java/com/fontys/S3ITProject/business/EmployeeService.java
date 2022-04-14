package com.fontys.s3itproject.business;

import com.fontys.s3itproject.models.Employee;

import java.util.List;

public interface EmployeeService {
    public boolean createEmployee(Employee employee);
    public List<Employee> readEmployees();
    public Employee readEmployeeByID(int id);
    public boolean updateEmployee(Employee employee);
    public boolean deleteEmployee(Employee employee);
}
