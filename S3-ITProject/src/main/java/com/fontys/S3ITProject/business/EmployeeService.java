package com.fontys.s3itproject.business;

import com.fontys.s3itproject.dto.CreateEmployeeRequestDTO;
import com.fontys.s3itproject.dto.CreateEmployeeResponseDTO;
import com.fontys.s3itproject.dto.GetEmployeesResponseDTO;

public interface EmployeeService {
    CreateEmployeeResponseDTO createEmployee(CreateEmployeeRequestDTO request);
    GetEmployeesResponseDTO getEmployees();
}
