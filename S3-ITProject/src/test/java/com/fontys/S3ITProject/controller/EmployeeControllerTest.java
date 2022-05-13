package com.fontys.s3itproject.controller;

import com.fontys.s3itproject.business.EmployeeService;
import com.fontys.s3itproject.dto.CreateEmployeeRequestDTO;
import com.fontys.s3itproject.dto.CreateEmployeeResponseDTO;
import com.fontys.s3itproject.dto.EmployeeDTO;
import com.fontys.s3itproject.dto.GetEmployeesResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeServiceMock;

    @Test
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
    void createEmployee_shouldCreateEmployeeAndReturn201_whenRequestValid() throws Exception{
        // create the employee dto we want to save
        CreateEmployeeRequestDTO requestDTO = CreateEmployeeRequestDTO.builder()
                .firstName("Esther")
                .lastName("Wolfs")
                .email("estherwolfs@goldskye.com")
                .phoneNumber("+31612901749")
                .dateOfBirth(LocalDate.of(1998,1,1))
                .password("password1")
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
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            {"employeeID":  1}
                        """));

        verify(employeeServiceMock).createEmployee(requestDTO);
    }

    @Test
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
                                {"field": "email", "error":  "must not be blank"},
                                {"field": "phoneNumber", "error":  "must not be blank"},
                                {"field": "dateOfBirth", "error":  "must not be null"},
                                {"field": "firstName", "error":  "length must be between 2 and 25"},
                                {"field": "lastName", "error":  "length must be between 2 and 50"},
                                {"field": "email", "error":  "length must be between 2 and 50"},
                                {"field": "password", "error":  "must not be blank"},
                                {"field": "streetName", "error":  "must not be blank"},
                                {"field": "zipCode", "error":  "must not be blank"},
                                {"field": "city", "error":  "must not be blank"},
                                {"field": "zipCode", "error": "length must be between 6 and 6"},
                                {"field": "password", "error":  "length must be between 8 and 50"},
                                {"field": "city", "error":  "length must be between 2 and 50"},
                                {"field": "streetName", "error": "length must be between 2 and 50"}
                            ]
                        """));

        verifyNoInteractions(employeeServiceMock);
    }
}