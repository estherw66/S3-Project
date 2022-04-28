package com.fontys.s3itproject.controller;

import com.fontys.s3itproject.business.EmployeeService;
import com.fontys.s3itproject.dto.CreateEmployeeRequestDTO;
import com.fontys.s3itproject.dto.CreateEmployeeResponseDTO;
import com.fontys.s3itproject.dto.GetEmployeesResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<GetEmployeesResponseDTO> getEmployees(){
        return ResponseEntity.ok(employeeService.getEmployees());
    }

    @PostMapping
    public ResponseEntity<CreateEmployeeResponseDTO> createEmployee(
            @RequestBody @Valid CreateEmployeeRequestDTO createEmployeeRequest){
        CreateEmployeeResponseDTO response = employeeService.createEmployee(createEmployeeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
