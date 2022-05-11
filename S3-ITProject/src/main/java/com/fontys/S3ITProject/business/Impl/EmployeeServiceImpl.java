package com.fontys.s3itproject.business.impl;

import com.fontys.s3itproject.business.EmployeeService;
import com.fontys.s3itproject.business.exception.InvalidEmployeeException;
import com.fontys.s3itproject.dto.CreateEmployeeRequestDTO;
import com.fontys.s3itproject.dto.CreateEmployeeResponseDTO;
import com.fontys.s3itproject.dto.EmployeeDTO;
import com.fontys.s3itproject.dto.GetEmployeesResponseDTO;
import com.fontys.s3itproject.repository.EmployeeRepository;
import com.fontys.s3itproject.repository.UserRepository;
import com.fontys.s3itproject.repository.entity.Employee;
import com.fontys.s3itproject.repository.entity.RoleEnum;
import com.fontys.s3itproject.repository.entity.User;
import com.fontys.s3itproject.repository.entity.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private static final String EMAIL_SUFFIX = "@goldskye.com";

    private final PasswordEncoder passwordEncoder;

    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;

    @Override
    public CreateEmployeeResponseDTO createEmployee(CreateEmployeeRequestDTO request) {
        if (existsByEmail(request.getEmail())){
            throw new InvalidEmployeeException("EMAIL_DUPLICATED");
        }

        Employee savedEmployee = saveNewEmployee(request);

        saveNewUser(savedEmployee, request.getPassword());

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

    private void saveNewUser(Employee employee, String password){
        String encodedPassword = passwordEncoder.encode(password);

        User newUser = User.builder()
                .username(employee.getFirstName() + employee.getLastName())
                .password(encodedPassword)
                .employee(employee)
                .build();

        newUser.setUserRoles(Set.of(
                UserRole.builder()
                        .user(newUser)
                        .role(RoleEnum.EMPLOYEE)
                        .build()));

        userRepository.save(newUser);
    }

    private Employee saveNewEmployee(CreateEmployeeRequestDTO request){
        Employee newEmployee = Employee.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getFirstName() + request.getLastName() + EMAIL_SUFFIX)
                .phoneNumber(request.getPhoneNumber())
                .dateOfBirth(request.getDateOfBirth())
                .build();


        return employeeRepository.save(newEmployee);
    }

    private boolean existsByEmail(String email){
        return employeeRepository.existsByEmail(email);
    }

    private List<Employee> findAll(){
        return employeeRepository.findAll();
    }
}