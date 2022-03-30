package com.fontys.S3ITProject.persistence;

import com.fontys.S3ITProject.models.Address;
import com.fontys.S3ITProject.models.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FakeDataBaseTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void createNewEmployeeSuccessful() {
        // arrange
        FakeDataBaseImpl fdb = new FakeDataBaseImpl();
        Employee testEmployee1 = new Employee(6, "Henk", "Jansen", "h.jansen@goldskye.com", "password", new Address("street", "1234AA", "Eindhoven"), "+31612345678", LocalDate.of(2000,12,12));

        // act
        boolean result = fdb.createEmployee(testEmployee1);

        // assert
        assertEquals(true, result);
    }

    @Test
    void createNewEmployeeIDAlreadyExists(){
        // arrange
        FakeDataBaseImpl fdb = new FakeDataBaseImpl();
        Employee testEmployee1 = new Employee(1, "Henk", "Jansen", "h.jansen@goldskye.com", "password", new Address("street", "1234AA", "Eindhoven"), "+31612345678", LocalDate.of(2000,12,12));

        // act
        boolean result = fdb.createEmployee(testEmployee1);

        // assert
        assertEquals(false, result);
    }

    @Test
    void readEmployeesContainsFiveEmployees() {
        // arrange
        FakeDataBaseImpl fdb = new FakeDataBaseImpl();

        // act
        long result = fdb.readEmployees().stream().count();

        // assert
        assertEquals(5, result);
    }

    @Test
    void readEmployeeByIDGetNameEmployeeWithIDFive() {
        // arrange
        FakeDataBaseImpl fdb = new FakeDataBaseImpl();

        // act
        Employee testEmployee = fdb.readEmployeeByID(5);

        // assert

        assertEquals("Kate", testEmployee.getFirstName());
    }

    @Test
    void readEmployeeByIDGetNameEmployeeWithIDThatDoesntExist(){
        // arrange
        FakeDataBaseImpl fdb = new FakeDataBaseImpl();

        // act
        Employee testEmployee = fdb.readEmployeeByID(6);

        boolean result = false;
        if (testEmployee != null){
             result = true;
        }

        // assert
        assertEquals(false, result);
    }

    @Test
    void updateEmployeeEmail() {
        // arrange
        FakeDataBaseImpl fdb = new FakeDataBaseImpl();
        Employee old = fdb.readEmployeeByID(5);

        // act

        old.setEmail("k.price@goldskye.com");
        fdb.updateEmployee(old);

        Employee updated = fdb.readEmployeeByID(5);

        // assert
        assertEquals("k.price@goldskye.com", updated.getEmail());
    }

    @Test
    void deleteEmployee() {
        // arrange
        FakeDataBaseImpl fdb = new FakeDataBaseImpl();

        // act
        fdb.deleteEmployee(5);
        long result = fdb.readEmployees().stream().count();

        // assert
        assertEquals(4, result);
    }
}