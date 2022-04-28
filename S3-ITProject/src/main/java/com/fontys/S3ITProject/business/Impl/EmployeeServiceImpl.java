package com.fontys.s3itproject.business.impl;

import com.fontys.s3itproject.business.EmployeeService;
import com.fontys.s3itproject.business.exception.InvalidEmployeeException;
import com.fontys.s3itproject.dto.CreateEmployeeRequestDTO;
import com.fontys.s3itproject.dto.CreateEmployeeResponseDTO;
import com.fontys.s3itproject.dto.EmployeeDTO;
import com.fontys.s3itproject.dto.GetEmployeesResponseDTO;
import com.fontys.s3itproject.repository.EmployeeRepository;
import com.fontys.s3itproject.repository.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public CreateEmployeeResponseDTO createEmployee(CreateEmployeeRequestDTO request) {
        if (existsByEmail(request.getEmail())){
            throw new InvalidEmployeeException("EMAIL_DUPLICATED");
        }

        Employee newEmployee = Employee.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .dateOfBirth(request.getDateOfBirth())
                .build();

        Employee savedEmployee = save(newEmployee);

        return CreateEmployeeResponseDTO.builder()
                .employeeID(savedEmployee.getId())
                .build();
    }

    @Override
    public GetEmployeesResponseDTO getEmployees() {
        List<EmployeeDTO> employees = findAll()
                .stream()
                .map(EmployeeDTOConverter::convertToDTO)
                .toList();

        return GetEmployeesResponseDTO.builder()
                .employees(employees)
                .build();
    }

    private Employee save(Employee employee){
        return employeeRepository.save(employee);
    }

    private boolean existsByEmail(String email){
        return employeeRepository.existsByEmail(email);
    }

    private List<Employee> findAll(){
        return employeeRepository.findAll();
    }
}