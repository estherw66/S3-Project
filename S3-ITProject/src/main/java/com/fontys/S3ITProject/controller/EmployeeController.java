package com.fontys.s3itproject.controller;

import com.fontys.s3itproject.business.EmployeeService;
import com.fontys.s3itproject.business.impl.EmployeeServiceImpl;
import com.fontys.s3itproject.persistence.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {

}
