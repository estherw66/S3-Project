package com.fontys.s3itproject.business;

import com.fontys.s3itproject.dto.*;

import java.util.Optional;

public interface EmployeeService {
    CreateEmployeeResponseDTO createEmployee(CreateEmployeeRequestDTO request);
    GetEmployeesResponseDTO getEmployees();
    Optional<EmployeeDTO> getEmployee(long employeeID);
    void updateEmployee(UpdateEmployeeRequestDTO request);
    void deleteEmployee(Long employeeID);
}
