package com.fontys.S3ITProject.Repository;

import com.fontys.S3ITProject.Models.Employee;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Primary
public class FakeDataBase {

    private final List<Employee> employees = new ArrayList<>();
}
