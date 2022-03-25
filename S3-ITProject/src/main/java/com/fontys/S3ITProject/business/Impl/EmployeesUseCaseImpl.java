package com.fontys.S3ITProject.business.Impl;

import com.fontys.S3ITProject.business.EmployeesUseCase;
import com.fontys.S3ITProject.models.Employee;
import com.fontys.S3ITProject.persistence.FakeDataBase;

import java.util.List;

public class EmployeesUseCaseImpl implements EmployeesUseCase {

    private final FakeDataBase fakeDataBase;

    public EmployeesUseCaseImpl(FakeDataBase fakeDataBase){
        this.fakeDataBase = fakeDataBase;
    }

    @Override
    public boolean createEmployee(Employee employee) {
        return this.fakeDataBase.createEmployee(employee);
    }

    @Override
    public List<Employee> readEmployees() {
        return this.fakeDataBase.readEmployees();
    }

    @Override
    public Employee readEmployeeByID(int id) {
        return this.fakeDataBase.readEmployeeByID(id);
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        return this.fakeDataBase.updateEmployee(employee);
    }

    @Override
    public boolean deleteEmployee(int employeeID) {
        return this.fakeDataBase.deleteEmployee(employeeID);
    }
}
