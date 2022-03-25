package com.fontys.S3ITProject.persistence;

import com.fontys.S3ITProject.models.Employee;
import com.fontys.S3ITProject.models.Guest;

import java.util.List;

public interface FakeDataBase {

    // employees
    public boolean createEmployee(Employee employee);
    public List<Employee> readEmployees();
    public Employee readEmployeeByID(int id);
    public boolean updateEmployee(Employee employee);
    public boolean deleteEmployee(int employeeID);

    // guests
    public List<Guest> readGuests();
    public Guest readGuestByID(int id);

}
