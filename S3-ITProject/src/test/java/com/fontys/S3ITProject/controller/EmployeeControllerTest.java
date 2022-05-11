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
                                    "dateOfBirth": "1998-01-01"
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
}