package com.fontys.s3itproject.controller;

import com.fontys.s3itproject.business.EmployeeService;
import com.fontys.s3itproject.configuration.security.isauthenticated.IsAuthenticated;
import com.fontys.s3itproject.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {
    private final EmployeeService employeeService;

//    @IsAuthenticated
//    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_ADMIN"})
    @GetMapping
    public ResponseEntity<GetEmployeesResponseDTO> getEmployees(){
        return ResponseEntity.ok(employeeService.getEmployees());
    }

//    @IsAuthenticated
//    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_ADMIN"})
    @GetMapping(path = "{id}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable(value = "id") final long id){
        final Optional<EmployeeDTO> employeeOptional = employeeService.getEmployee(id);
        if (employeeOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(employeeOptional.get());
    }

//    @IsAuthenticated
//    @RolesAllowed({"ROLE_ADMIN"})
    @PostMapping
    public ResponseEntity<CreateEmployeeResponseDTO> createEmployee(
            @RequestBody @Valid CreateEmployeeRequestDTO createEmployeeRequest){
        CreateEmployeeResponseDTO response = employeeService.createEmployee(createEmployeeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

//    @IsAuthenticated
//    @RolesAllowed({"ROLE_ADMIN", "ROLE_EMPLOYEE"})
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable(value = "id") long id,
                                                      @RequestBody @Valid UpdateEmployeeRequestDTO requestDTO){
        requestDTO.setId(id);
        employeeService.updateEmployee(requestDTO);
        return ResponseEntity.noContent().build();
    }

//    @IsAuthenticated
//    @RolesAllowed({"ROLE_ADMIN"})
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable long id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
