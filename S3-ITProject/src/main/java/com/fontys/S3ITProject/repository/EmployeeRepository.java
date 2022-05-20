package com.fontys.s3itproject.repository;

import com.fontys.s3itproject.repository.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);

}
