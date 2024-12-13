package com.fontys.s3itproject.repository;

import com.fontys.s3itproject.repository.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByPhoneNumber(String phoneNumber);
}
