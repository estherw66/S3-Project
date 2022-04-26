package com.fontys.s3itproject.controller;

import com.fontys.s3itproject.business.EmployeeService;
import com.fontys.s3itproject.business.impl.EmployeeServiceImpl;
import com.fontys.s3itproject.entity.Employee;
import com.fontys.s3itproject.persistence.impl.EmployeeRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService = new EmployeeServiceImpl(new EmployeeRepositoryImpl());

    // get all employees
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> employees = employeeService.readEmployees();

        if (employees != null){
            return ResponseEntity.ok().body(employees);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        employeeService.createEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }
}
