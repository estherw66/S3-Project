package com.fontys.S3ITProject.Business;

import com.fontys.S3ITProject.Models.Employee;

import java.util.List;

public interface EmployeesUseCase {
    public boolean createEmployee(Employee employee);
    public List<Employee> readEmployees();
    public Employee readEmployeeByID(int id);
    public boolean updateEmployee(Employee employee);
    public boolean deleteEmployee(int employeeID);
}
