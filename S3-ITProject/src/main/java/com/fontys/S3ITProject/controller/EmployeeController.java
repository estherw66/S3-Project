package com.fontys.S3ITProject.controller;

import com.fontys.S3ITProject.business.EmployeeService;
import com.fontys.S3ITProject.models.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

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
}
