package com.fontys.s3itproject.business.impl;

import com.fontys.s3itproject.business.EmployeeService;
import com.fontys.s3itproject.business.exception.InvalidEmployeeException;
import com.fontys.s3itproject.business.exception.UnauthorisedDataAccessException;
import com.fontys.s3itproject.dto.*;
import com.fontys.s3itproject.repository.AddressRepository;
import com.fontys.s3itproject.repository.EmployeeRepository;
import com.fontys.s3itproject.repository.UserRepository;
import com.fontys.s3itproject.repository.entity.*;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private static final String EMAIL_SUFFIX = "@goldskye.com";

    private final PasswordEncoder passwordEncoder;
    private final AccessTokenDTO requestAccessToken;

    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    @Override
    public CreateEmployeeResponseDTO createEmployee(CreateEmployeeRequestDTO request) {
        if (existsByPhoneNumber(request.getPhoneNumber())){
            throw new InvalidEmployeeException("PHONE_NUMBER_DUPLICATED");
        }

        if (request.getDateOfBirth().isAfter(LocalDate.now().minusYears(16))){
            throw new InvalidEmployeeException("EMPLOYEE_SHOULD_BE_AT_LEAST_16_YEARS_OLD");
        }

        Employee savedEmployee = saveNewEmployee(request);

        saveNewUser(savedEmployee, "password123");
        saveNewAddress(savedEmployee, request.getStreetName(), request.getZipCode(), request.getCity());

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

    @Override
    public Optional<EmployeeDTO> getEmployee(long employeeID) {
        if (!requestAccessToken.hasRole(RoleEnum.ADMIN.name()) && requestAccessToken.getEmployeeId() != employeeID){
            throw new UnauthorisedDataAccessException("EMPLOYEE_ID_NOT_FROM_LOGGED_IN_USER");
        }

        return employeeRepository.findById(employeeID).map(EmployeeDTOConverter::convertToDTO);
    }

    @Override
    public void updateEmployee(UpdateEmployeeRequestDTO request) {
        Optional<Employee> employeeOptional = employeeRepository.findById(request.getId());
        if (employeeOptional.isEmpty()){
            throw new InvalidEmployeeException("EMPLOYEE_NOT_FOUND");
        }

        if (!requestAccessToken.hasRole(RoleEnum.ADMIN.name()) && !requestAccessToken.getEmployeeId().equals(request.getId())){
            throw new UnauthorisedDataAccessException("EMPLOYEE_ID_NOT_FROM_LOGGED_IN_USER");
        }

        Employee employee = employeeOptional.get();
        updateFields(request, employee);
    }

    @Override
    public void deleteEmployee(Long employeeID) {
        this.employeeRepository.deleteById(employeeID);
    }

    private void updateFields(UpdateEmployeeRequestDTO request, Employee employee) {
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setPhoneNumber(request.getPhoneNumber());

        employeeRepository.save(employee);
    }

    private void saveNewUser(Employee employee, String password){
        String encodedPassword = passwordEncoder.encode(password);

        User newUser = User.builder()
                .username(employee.getFirstName().toLowerCase() + employee.getLastName().toLowerCase())
                .password(encodedPassword)
                .employee(employee)
                .guest(null)
                .build();

        newUser.setUserRoles(Set.of(
                UserRole.builder()
                        .user(newUser)
                        .role(RoleEnum.EMPLOYEE)
                        .build()));

        userRepository.save(newUser);
    }

    private void saveNewAddress(Employee employee, String streetName, String zipCode, String city){
        Address newAddress = Address.builder()
                .streetName(streetName)
                .zipCode(zipCode)
                .city(city)
                .employee(employee)
                .build();

        addressRepository.save(newAddress);
    }

    private Employee saveNewEmployee(CreateEmployeeRequestDTO request){
        Employee newEmployee = Employee.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getFirstName().toLowerCase() + request.getLastName().toLowerCase() + EMAIL_SUFFIX)
                .phoneNumber(request.getPhoneNumber())
                .dateOfBirth(request.getDateOfBirth())
                .build();

        return employeeRepository.save(newEmployee);
    }

    private boolean existsByPhoneNumber(String phoneNumber) { return employeeRepository.existsByPhoneNumber(phoneNumber); }

    private List<Employee> findAll(){
        return employeeRepository.findAll();
    }
}