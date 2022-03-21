package com.fontys.S3ITProject.Controllers;

import com.fontys.S3ITProject.Models.Employee;
import com.fontys.S3ITProject.Repository.Impl.FakeDataBaseImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    private FakeDataBaseImpl fakeDataBase;

    public EmployeesController(FakeDataBaseImpl fakeDataBase){
        this.fakeDataBase = fakeDataBase;
    }

    // Create
    @PostMapping()
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        if (!fakeDataBase.createEmployee(employee)){
            String entity = "Employee with id " + employee.getEmployeeID() + " already exists";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            String url = "employees" + "/" + employee.getEmployeeID();
            URI uri = URI.create(url);
            return new ResponseEntity(uri, HttpStatus.CREATED);
        }
    }

    // Read all employees
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> employees = fakeDataBase.readEmployees();

        if (employees != null){
            return ResponseEntity.ok().body(employees);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Read employee by id
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeePath(@PathVariable(value = "id") int id){
        Employee employee = fakeDataBase.readEmployeeByID(id);

        if (employee != null){
            return ResponseEntity.ok().body(employee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update employee
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") int id, @RequestBody() Employee employee){
        Employee old = fakeDataBase.readEmployeeByID(id);

        if (old != null){
            old.setFirstName(employee.getFirstName());
            old.setLastName(employee.getLastName());
            old.setEmail(employee.getEmail());
            old.setPhoneNumber(employee.getPhoneNumber());
            old.setAddress(employee.getAddress());

            return ResponseEntity.noContent().build();
        }

        return new ResponseEntity("Please provide a valid employee id", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteEmployee(@PathVariable int id){
        fakeDataBase.deleteEmployee(id);

        return ResponseEntity.ok().build();
    }
}
