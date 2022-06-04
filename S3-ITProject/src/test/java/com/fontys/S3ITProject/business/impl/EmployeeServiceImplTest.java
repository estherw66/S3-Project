package com.fontys.s3itproject.business.impl;

import com.fontys.s3itproject.business.exception.EmailAlreadyExistsException;
import com.fontys.s3itproject.business.exception.InvalidCredentialsException;
import com.fontys.s3itproject.business.exception.InvalidEmployeeException;
import com.fontys.s3itproject.business.exception.UnauthorisedDataAccessException;
import com.fontys.s3itproject.dto.*;
import com.fontys.s3itproject.repository.AddressRepository;
import com.fontys.s3itproject.repository.EmployeeRepository;
import com.fontys.s3itproject.repository.UserRepository;
import com.fontys.s3itproject.repository.entity.*;
import lombok.AllArgsConstructor;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepositoryMock;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AccessTokenDTO accessTokenDTO;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private AccessTokenDTO getAccessTokenDTO() {
        return AccessTokenDTO.builder()
                .subject("Esther")
                .roles(List.of("ADMIN"))
                .employeeId(1L)
                .build();
    }

    @Test
    void createEmployee_shouldSaveNewEmployee() {
        when(employeeRepositoryMock.existsByPhoneNumber("+31612901749")).thenReturn(false);

        Employee esther = Employee.builder()
                .firstName("Esther")
                .lastName("Wolfs")
                .email("estherwolfs@goldskye.com")
                .dateOfBirth(LocalDate.of(1998,1,1))
                .phoneNumber("+31612901749")
                .build();

        Employee saved = Employee.builder()
                .id(1L)
                .firstName("Esther")
                .lastName("Wolfs")
                .email("estherwolfs@goldskye.com")
                .dateOfBirth(LocalDate.of(1998,1,1))
                .phoneNumber("+31612901749")
                .build();

        when(employeeRepositoryMock.save(esther))
                .thenReturn(saved);

        CreateEmployeeRequestDTO requestDTO = CreateEmployeeRequestDTO.builder()
                .firstName("Esther")
                .lastName("Wolfs")
                .dateOfBirth(LocalDate.of(1998,1,1))
                .phoneNumber("+31612901749")
                .streetName("Mozartlaan 41")
                .zipCode("5151KA")
                .city("Drunen")
                .build();

        CreateEmployeeResponseDTO actualResult = employeeService.createEmployee(requestDTO);

        CreateEmployeeResponseDTO expectedResult = CreateEmployeeResponseDTO.builder()
                .employeeID(1L)
                .build();

        assertEquals(expectedResult, actualResult);
        verify(employeeRepositoryMock).existsByPhoneNumber("+31612901749");
        verify(employeeRepositoryMock).save(esther);
    }

    @Test
    void createEmployee_shouldThrowInvalidEmployeeException_whenSavingNewEmployeeDuplicatedPhoneNumber(){
        when(employeeRepositoryMock.existsByPhoneNumber("+31612901749"))
                .thenReturn(true);

        CreateEmployeeRequestDTO request = CreateEmployeeRequestDTO.builder()
                .firstName("Esther")
                .lastName("Wolfs")
                .dateOfBirth(LocalDate.of(1998,1,1))
                .phoneNumber("+31612901749")
                .build();

        assertThrows(InvalidEmployeeException.class,() -> employeeService.createEmployee(request));
        verify(employeeRepositoryMock).existsByPhoneNumber("+31612901749");
    }

    @Test
    void getEmployees_shouldReturnAllEmployeesConvertedToDTO() {
        // create employee entities
        Employee esther = Employee.builder()
                .id(1L)
                .firstName("Esther")
                .lastName("Wolfs")
                .email("estherwolfs@goldskye.com")
                .dateOfBirth(LocalDate.of(1998,1,1))
                .phoneNumber("+31612901749")
                .address(Address.builder().id(1L)
                        .streetName("Mozartlaan 41").zipCode("5151KA")
                        .city("Drunen").build())
                .build();
        Employee john = Employee.builder()
                .id(2L)
                .firstName("John")
                .lastName("Floreani")
                .email("johnfloreani@goldskye.com")
                .dateOfBirth(LocalDate.of(1995,10,13))
                .phoneNumber("+31645887651")
                .address(Address.builder().id(2L)
                        .streetName("Rachelsmolen 1").zipCode("5151KA")
                        .city("Eindhoven").build())
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
                .dateOfBirth(LocalDate.of(1998,1,1))
                .phoneNumber("+31612901749")
                .address(AddressDTO.builder().id(1L).streetName("Mozartlaan 41")
                        .zipCode("5151KA").city("Drunen").build())
                .build();
        EmployeeDTO johnDTO = EmployeeDTO.builder()
                .id(2L)
                .firstName("John")
                .lastName("Floreani")
                .email("johnfloreani@goldskye.com")
                .dateOfBirth(LocalDate.of(1995,10,13))
                .address(AddressDTO.builder().id(2L).streetName("Rachelsmolen 1")
                        .zipCode("5151KA").city("Eindhoven").build())
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

    @Test
    void getEmployee_shouldReturnOptionalEmployeeByIDConvertedToDTO(){
        when(accessTokenDTO.getEmployeeId()).thenReturn(1L);

        Employee employee = Employee.builder()
                .id(1L)
                .firstName("Esther")
                .lastName("Wolfs")
                .email("estherwolfs@goldskye.com")
                .dateOfBirth(LocalDate.of(1998,1,1))
                .phoneNumber("+31612901749")
                .address(Address.builder().id(1L)
                        .streetName("Mozartlaan 41").zipCode("5151KA")
                        .city("Drunen").build())
                .build();

        when(employeeRepositoryMock.findById(1L))
                .thenReturn(Optional.of(employee));

        Optional<EmployeeDTO> actualResult = employeeService.getEmployee(1L);

        EmployeeDTO employeeDTO = EmployeeDTO.builder()
                .id(1L)
                .firstName("Esther")
                .lastName("Wolfs")
                .email("estherwolfs@goldskye.com")
                .dateOfBirth(LocalDate.of(1998,01,01))
                .phoneNumber("+31612901749")
                .address(AddressDTO.builder().id(1L).streetName("Mozartlaan 41")
                        .zipCode("5151KA").city("Drunen").build())
                .build();

        assertTrue(actualResult.isPresent());
        assertEquals(employeeDTO, actualResult.get());
        verify(employeeRepositoryMock).findById(1L);
    }

    @Test
    void updateEmployee_shouldUpdateEmployeePhoneNumber(){
        Employee updated = Employee.builder()
                .id(1L)
                .firstName("Esther")
                .lastName("Wolfs")
                .email("EstherWolfs@goldskye.com")
                .dateOfBirth(LocalDate.of(1998,1,1))
                .phoneNumber("+31612901749")
                .build();

        when(employeeRepositoryMock.findById(1L))
                .thenReturn(Optional.of(updated));

        UpdateEmployeeRequestDTO requestDTO = UpdateEmployeeRequestDTO.builder()
                .id(1L)
                .firstName("Esther")
                .lastName("Wolfs")
                .phoneNumber("+31617491290")
                .build();

        employeeService.updateEmployee(requestDTO);
        verify(employeeRepositoryMock).save(updated);
    }

    @Test
    void getEmployee_shouldReturn_shouldReturnUnauthorisedDataAccessException_whenEmployeeIDNotFromLoggedInUser() {
        User testUser = User.builder()
                .id(1L)
                .username("username")
                .password("hashed-password")
                .build();
        testUser.setUserRoles(Set.of(
                UserRole.builder()
                        .user(testUser)
                        .role(RoleEnum.EMPLOYEE)
                        .build()));

        AccessTokenDTO accessTokenDTO = AccessTokenDTO.builder()
                .subject("User")
                .roles(List.of("Employee"))
                .employeeId(2L)
                .build();

        UnauthorisedDataAccessException exception = assertThrows(UnauthorisedDataAccessException.class,
                () -> employeeService.getEmployee(1L));

        assertEquals("EMPLOYEE_ID_NOT_FROM_LOGGED_IN_USER", exception.getReason());
        verifyNoInteractions(employeeRepositoryMock);
    }


    @Test
    void deleteEmployee_shouldDeleteEmployee(){
        employeeService.deleteEmployee(0L);
        verify(employeeRepositoryMock).deleteById(0L);
    }
}