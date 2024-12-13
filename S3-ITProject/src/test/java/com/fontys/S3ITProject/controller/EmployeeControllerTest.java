package com.fontys.s3itproject.controller;

import com.fontys.s3itproject.business.EmployeeService;
import com.fontys.s3itproject.dto.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeServiceMock;

    @Test
    @WithMockUser(username = "Esther", roles = {"EMPLOYEE"})
    void getEmployee_shouldReturn200ResponseWithEmployee_whenEmployeeFound() throws Exception {
        EmployeeDTO employeeDTO = EmployeeDTO.builder()
                .id(1L)
                .firstName("Esther")
                .lastName("Wolfs")
                .email("EstherWolfs@goldskye.com")
                .dateOfBirth(LocalDate.of(1998,01,01))
                .phoneNumber("+31612901749")
                .address(AddressDTO.builder().streetName("Mozartlaan 41").zipCode("5151KA").city("Drunen").build())
                .build();

        when(employeeServiceMock.getEmployee(1L))
                .thenReturn(Optional.of(employeeDTO));

        mockMvc.perform(get("/api/employees/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            {
                                "id": 1,
                                "firstName": "Esther",
                                "lastName": "Wolfs",
                                "email": "EstherWolfs@goldskye.com",
                                "phoneNumber": "+31612901749",
                                "dateOfBirth": "1998-01-01",
                                "address": {
                                    "streetName": "Mozartlaan 41",
                                    "zipCode": "5151KA",
                                    "city": "Drunen"
                                }
                            }
                    """));

        verify(employeeServiceMock).getEmployee(1L);
    }

    @Test
    @WithMockUser(username = "Esther", roles = {"EMPLOYEE"})
    void getEmployee_shouldReturn404NotFoundError_whenEmployeeNotFound() throws Exception {
        when(employeeServiceMock.getEmployee(1L))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/api/employees/1"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(employeeServiceMock).getEmployee(1L);
    }

    @Test
    @WithMockUser(username = "EstherWolfs", roles = {"ADMIN"})
    void getEmployees_shouldReturn200ResponseWithEmployeesArray() throws Exception {
        // create the list of employees that we want to be returned
        GetEmployeesResponseDTO responseDTO = GetEmployeesResponseDTO
                .builder()
                .employees(List.of(
                        EmployeeDTO.builder().id(1L)
                                .firstName("Esther").lastName("Wolfs").email("estherwolfs@goldskye.com")
                                .phoneNumber("+31612901749").dateOfBirth(LocalDate.of(1998,1,1))
                                .build(),
                        EmployeeDTO.builder().id(2L)
                                .firstName("John").lastName("Floreani").email("johnfloreani@goldskye.com")
                                .phoneNumber("+31648265971").dateOfBirth(LocalDate.of(1993,12,31))
                                .build()
                ))
                .build();
        when(employeeServiceMock.getEmployees())
                .thenReturn(responseDTO);

        mockMvc.perform(get("/api/employees"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
{"employees":[{"id":1, "firstName":"Esther", "lastName": "Wolfs", "email": "estherwolfs@goldskye.com", "phoneNumber": "+31612901749", "dateOfBirth": "1998-01-01"}
,{"id":2, "firstName":"John", "lastName": "Floreani", "email": "johnfloreani@goldskye.com", "phoneNumber": "+31648265971", "dateOfBirth": "1993-12-31"}]}
"""));

        verify(employeeServiceMock).getEmployees();
    }

    @Test
    @WithMockUser(username = "EstherWolfs", roles = {"ADMIN"})
    void createEmployee_shouldCreateEmployeeAndReturn201_whenRequestValid() throws Exception{
        // create the employee dto we want to save
        CreateEmployeeRequestDTO requestDTO = CreateEmployeeRequestDTO.builder()
                .firstName("Esther")
                .lastName("Wolfs")
                .phoneNumber("+31612901749")
                .dateOfBirth(LocalDate.of(1998,1,1))
                .streetName("Mozartlaan 41")
                .zipCode("5151KA")
                .city("Drunen")
                .build();
        when(employeeServiceMock.createEmployee(requestDTO))
                .thenReturn(CreateEmployeeResponseDTO.builder()
                        .employeeID(1L).build());

        mockMvc.perform(post("/api/employees")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                    "firstName": "Esther",
                                    "lastName": "Wolfs",
                                    "email": "estherwolfs@goldskye.com",
                                    "phoneNumber": "+31612901749",
                                    "dateOfBirth": "1998-01-01",
                                    "password": "password1",
                                    "streetName": "Mozartlaan 41",
                                    "zipCode": "5151KA",
                                    "city": "Drunen"
                                }
                                """))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json("""
                            {"employeeID":  1}
                        """));

        verify(employeeServiceMock).createEmployee(requestDTO);
    }

    @Test
    @WithMockUser(username = "EstherWolfs", roles = {"ADMIN"})
    void createEmployee_shouldNotCreateEmployeeAndReturn400_WhenMissingFields() throws Exception{
        mockMvc.perform(post("/api/employees")
                .contentType(APPLICATION_JSON_VALUE)
                .content("""
                            {
                                "firstName": "",
                                "lastName": "",
                                "email": "",
                                "phoneNumber": "",
                                "dateOfBirth": "",
                                "password": "",
                                "streetName": "",
                                "zipCode": "",
                                "city": ""
                            }
                        """))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            [
                                {"field": "firstName", "error":  "must not be blank"},
                                {"field": "lastName", "error":  "must not be blank"},
                                {"field": "phoneNumber", "error":  "must not be blank"},
                                {"field": "dateOfBirth", "error":  "must not be null"},
                                {"field": "firstName", "error":  "length must be between 2 and 25"},
                                {"field": "lastName", "error":  "length must be between 2 and 50"},
                                {"field": "streetName", "error":  "must not be blank"},
                                {"field": "zipCode", "error":  "must not be blank"},
                                {"field": "city", "error":  "must not be blank"},
                                {"field": "zipCode", "error": "length must be between 6 and 6"},
                                {"field": "city", "error":  "length must be between 2 and 50"},
                                {"field": "streetName", "error": "length must be between 2 and 50"}
                            ]
                        """));

        verifyNoInteractions(employeeServiceMock);
    }

    @Test
    @WithMockUser(username = "Esther", roles = {"ADMIN"})
    void deleteEmployee_shouldReturn204_whenDeleteEmployee() throws Exception {
        mockMvc.perform(delete("/api/employees/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
        verify(employeeServiceMock).deleteEmployee(1L);
    }

    @Test
    @WithMockUser(username = "Esther", roles = {"EMPLOYEE"})
    void updateEmployee_shouldReturn204_whenUpdatingEmployee() throws Exception {
        mockMvc.perform(put("/api/employees/1")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                                    {
                                        "firstName": "Esther",
                                        "lastName": "Wolfs",
                                        "phoneNumber": "+31612901749"
                                    }
                                """))
                .andDo(print())
                .andExpect(status().isNoContent());

        UpdateEmployeeRequestDTO expectedRequest = UpdateEmployeeRequestDTO.builder()
                .id(1L)
                .firstName("Esther")
                .lastName("Wolfs")
                .phoneNumber("+31612901749")
                .build();
        verify(employeeServiceMock).updateEmployee(expectedRequest);
    }
}