package com.fontys.s3itproject.controller;

import com.fontys.s3itproject.business.EmployeeService;
import com.fontys.s3itproject.configuration.security.isauthenticated.IsAuthenticated;
import com.fontys.s3itproject.dto.CreateEmployeeRequestDTO;
import com.fontys.s3itproject.dto.CreateEmployeeResponseDTO;
import com.fontys.s3itproject.dto.GetEmployeesResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {
    private final EmployeeService employeeService;

    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_ADMIN"})
    @GetMapping
    public ResponseEntity<GetEmployeesResponseDTO> getEmployees(){
        return ResponseEntity.ok(employeeService.getEmployees());
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_ADIM"})
    @PostMapping
    public ResponseEntity<CreateEmployeeResponseDTO> createEmployee(
            @RequestBody @Valid CreateEmployeeRequestDTO createEmployeeRequest){
        CreateEmployeeResponseDTO response = employeeService.createEmployee(createEmployeeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
