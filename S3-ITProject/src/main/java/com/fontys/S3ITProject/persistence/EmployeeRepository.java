package com.fontys.s3itproject.persistence;

import com.fontys.s3itproject.models.Employee;

import java.util.List;

public interface EmployeeRepository {
    public boolean createEmployee(Employee e);
    public List<Employee> readEmployees();
    public Employee readEmployeeByID(int id);
    public boolean updateEmployee(Employee e);
    public boolean deleteEmployee(Employee e);
}
