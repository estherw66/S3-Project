package com.fontys.s3itproject.persistence;

import com.fontys.s3itproject.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface EmployeeRepository {
    boolean createEmployee(Employee e);
    List<Employee> readEmployees();
    Employee readEmployeeByID(Long id);
    boolean updateEmployee(Employee e);
    boolean deleteEmployee(Employee e);
}
