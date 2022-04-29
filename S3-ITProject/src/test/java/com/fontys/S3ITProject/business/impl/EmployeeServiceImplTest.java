package com.fontys.s3itproject.business.impl;

import com.fontys.s3itproject.business.EmployeeService;
import com.fontys.s3itproject.business.exception.InvalidEmployeeException;
import com.fontys.s3itproject.dto.*;
import com.fontys.s3itproject.repository.EmployeeRepository;
import com.fontys.s3itproject.repository.entity.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepositoryMock;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void createEmployee_shouldSaveNewEmployee() {
        when(employeeRepositoryMock.existsByEmail("estherwolfs@goldskye.com")).thenReturn(false);
        Employee esther = Employee.builder()
                .firstName("Esther")
                .lastName("Wolfs")
                .email("estherwolfs@goldskye.com")
                .dateOfBirth(new Date(1998,01,01))
                .phoneNumber("+31612901749")
                .build();

        // Employee saved = employeeRepositoryMock.save(esther)); // why not get the employee from the method???????
        Employee saved = Employee.builder()
                .id(1L)
                .firstName("Esther")
                .lastName("Wolfs")
                .email("estherwolfs@goldskye.com")
                .dateOfBirth(new Date(1998,01,01))
                .phoneNumber("+31612901749")
                .build();

        when(employeeRepositoryMock.save(esther))
                .thenReturn(saved);

        CreateEmployeeRequestDTO requestDTO = CreateEmployeeRequestDTO.builder()
                .firstName("Esther")
                .lastName("Wolfs")
                .email("estherwolfs@goldskye.com")
                .dateOfBirth(new Date(1998,01,01))
                .phoneNumber("+31612901749")
                .build();

        CreateEmployeeResponseDTO actualResult = employeeService.createEmployee(requestDTO);

        CreateEmployeeResponseDTO expectedResult = CreateEmployeeResponseDTO.builder()
                .employeeID(1L)
                .build();

        assertEquals(expectedResult, actualResult);
        verify(employeeRepositoryMock).existsByEmail("estherwolfs@goldskye.com");
        verify(employeeRepositoryMock).save(esther);
    }

    @Test
    void createEmployee_shouldThrowInvalidEmployeeException_whenSavingNewEmployee(){
        when(employeeRepositoryMock.existsByEmail("estherwolfs@goldskye.com"))
                .thenReturn(true);

        CreateEmployeeRequestDTO request = CreateEmployeeRequestDTO.builder()
                .firstName("Esther")
                .lastName("Wolfs")
                .email("estherwolfs@goldskye.com")
                .dateOfBirth(new Date(1998,01,01))
                .phoneNumber("+31612901749")
                .build();

        assertThrows(InvalidEmployeeException.class,() -> employeeService.createEmployee(request));
        verify(employeeRepositoryMock).existsByEmail("estherwolfs@goldskye.com");
    }

    @Test
    void getEmployees_shouldReturnAllEmployeesConvertedToDTO() {
        // create employee entities
        Employee esther = Employee.builder()
                .id(1L)
                .firstName("Esther")
                .lastName("Wolfs")
                .email("estherwolfs@goldskye.com")
                .dateOfBirth(new Date(1998,01,01))
                .phoneNumber("+31612901749")
                .build();
        Employee john = Employee.builder()
                .id(2L)
                .firstName("John")
                .lastName("Floreani")
                .email("johnfloreani@goldskye.com")
                .dateOfBirth(new Date(1995,10,13))
                .phoneNumber("+31645887651")
                .build();

        // call mocked method and configure the return
        when(employeeRepositoryMock.findAll()) // when find all is called
                .thenReturn(List.of(esther, john)); // a list of esther & john object is returned

        // invoke the method you want to test -> save result in variable
        GetEmployeesResponseDTO actualResult = employeeService.getEmployees(); // actual list that is returned

        // create employee DTOs
        EmployeeDTO estherDTO = EmployeeDTO.builder()
                .id(1L)
                .firstName("Esther")
                .lastName("Wolfs")
                .email("estherwolfs@goldskye.com")
                .dateOfBirth(new Date(1998,01,01))
                .phoneNumber("+31612901749")
                .build();
        EmployeeDTO johnDTO = EmployeeDTO.builder()
                .id(2L)
                .firstName("John")
                .lastName("Floreani")
                .email("johnfloreani@goldskye.com")
                .dateOfBirth(new Date(1995,10,13))
                .phoneNumber("+31645887651")
                .build();

        // save the expected result in a variable
        GetEmployeesResponseDTO expectedResult = GetEmployeesResponseDTO
                .builder()
                .employees(List.of(estherDTO, johnDTO))
                .build();

        // assert the expected result and the actual result
        assertEquals(expectedResult, actualResult);
        verify(employeeRepositoryMock).findAll();
    }
}