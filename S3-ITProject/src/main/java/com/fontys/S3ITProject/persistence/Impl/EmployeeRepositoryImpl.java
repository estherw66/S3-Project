package com.fontys.s3itproject.persistence.impl;

import com.fontys.s3itproject.entity.Employee;
import com.fontys.s3itproject.persistence.EmployeeRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;


@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private RepositoryImpl repository = new RepositoryImpl();


    @Override
    public boolean createEmployee(Employee e) {
        if (readEmployeeByID(e.getId()) != null){
            repository.employees.add(e);
        }

        return false;
    }

    @Override
    public List<Employee> readEmployees() {
        return repository.employees;
    }

    @Override
    public Employee readEmployeeByID(Long id) {
        for (Employee employee : repository.employees){
            if(employee.getId() == id){
                return employee;
            }
        }

        return null;
    }

    @Override
    public boolean updateEmployee(Employee e) {
        return false;
    }

    @Override
    public boolean deleteEmployee(Employee e) {
        if (readEmployeeByID(e.getId()) != null){
            repository.employees.remove(e);
            return true;
        }

        return false;
    }
}
