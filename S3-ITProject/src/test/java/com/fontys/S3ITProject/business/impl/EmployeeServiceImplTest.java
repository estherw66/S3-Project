package com.fontys.s3itproject.business.impl;

import com.fontys.s3itproject.business.EmployeeService;
import com.fontys.s3itproject.dto.EmployeeDTO;
import com.fontys.s3itproject.dto.GetEmployeesResponseDTO;
import com.fontys.s3itproject.repository.EmployeeRepository;
import com.fontys.s3itproject.repository.entity.Employee;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceImplTest {

    @Test
    void createEmployee() {
    }

    @Test
    void getEmployees_shouldReturnAllEmployeesConvertedToDTO() {
        // create mock instance of EmployeeRepository
        EmployeeRepository employeeRepositoryMock = mock(EmployeeRepository.class);

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

        // create instance of test class -> pass mock repo in constructor
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl(employeeRepositoryMock);

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