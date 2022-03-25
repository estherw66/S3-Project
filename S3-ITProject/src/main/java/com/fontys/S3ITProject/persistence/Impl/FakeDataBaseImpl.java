package com.fontys.S3ITProject.persistence.Impl;


import com.fontys.S3ITProject.models.*;
import com.fontys.S3ITProject.persistence.FakeDataBase;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;

@Repository
@Primary
public class FakeDataBaseImpl implements FakeDataBase {

    private final List<Employee> employees = new ArrayList<>();
    private final List<Guest> guests = new ArrayList<>();
    private final List<Reservation> reservations = new ArrayList<>();

    public FakeDataBaseImpl(){
        // employees
        employees.add(new Employee(1,"Esther", "Wolfs", "e.wolfs@goldskye.com", "password", new Address("Mozartlaan 41", "5151KA", "Drunen"), "+31612901749", LocalDate.of(1998,01,01)));
        employees.add(new Employee(2,"Emma", "Wiles", "e.wiles@goldskye.com", "password", new Address("Leidsestraat 41", "4864AD", "Amsterdam"), "+31678453321", LocalDate.of(1999, 04, 19)));
        employees.add(new Employee(3,"Jessy", "Avery", "j.avery@goldskye.com", "password", new Address("Kalverstraat 12", "4784AE", "Amsterdam"), "+31612783584", LocalDate.of(1998,01, 06)));
        employees.add(new Employee(4,"David", "Davis", "d.davis@goldskye.com", "password", new Address("Ooststraat 7", "7845JG", "Amsterdam"), "+31645886636", LocalDate.of(1995, 6, 30)));
        employees.add(new Employee(5,"Kate", "Price", "k.price@skyegold.com", "password", new Address("Coates Gardens 2", "4571IE", "Zaandam"), "+31644547382", LocalDate.of(1973, 4, 10)));

        // guests
        guests.add(new Guest(1,"John", "Floreani", "jflor@gmail.com", "password", new CreditCard("4780 0054 4578", "J. Floreani", "04/23")));
        guests.add(new Guest(2,"Jan", "Pieters", "janpieters@gmail.com", "password", null));
        guests.add(new Guest(3,"Henk", "Jansen", "henkjansen@hotmail.com", "password", null));

        // reservations
        reservations.add(new Reservation(1, LocalDate.of(2022,2, 10), LocalDate.of(2022, 2, 28), LocalDate.of(2022, 3, 3), new Guest(1,"John", "Floreani", "jflor@gmail.com", "password", new CreditCard("4780 0054 4578", "J. Floreani", "04/23")), 1, 100));
        reservations.add(new Reservation(2, LocalDate.of(2022,2, 18), LocalDate.of(2022, 4, 1), LocalDate.of(2022, 4, 7), new Guest(2,"Jan", "Pieters", "janpieters@gmail.com", "password", null), 2, 150));
        reservations.add(new Reservation(3, LocalDate.of(2022,2, 20), LocalDate.of(2022, 6, 15), LocalDate.of(2022, 6, 18), new Guest(3,"Henk", "Jansen", "henkjansen@hotmail.com", "password", null), 1, 75));
    }

    // employees
    public boolean createEmployee(Employee employee){
        if (this.readEmployeeByID(employee.getEmployeeID()) != null){
            return false;
        }

        employees.add(employee);
        return true;
    }

    public List<Employee> readEmployees(){
        return employees;
    }

    public Employee readEmployeeByID(int id){
        for (Employee e : employees){
            if (e.getEmployeeID() == id){
                return e;
            }
        }

        return null;
    }

    public boolean updateEmployee(Employee employee){
        Employee old = this.readEmployeeByID(employee.getEmployeeID());

        if (old != null){
            old.setFirstName(employee.getFirstName());
            old.setLastName(employee.getLastName());
            old.setPhoneNumber(employee.getPhoneNumber());
            old.setAddress(employee.getAddress());

            return true;
        }

        return false;
    }

    public boolean deleteEmployee(int employeeID){
        Employee employee = readEmployeeByID(employeeID);

        if(employee == null){
            return false;
        }

        return employees.remove(employee);
    }

    // guests
    public List<Guest> readGuests(){
        return guests;
    }

    public Guest readGuestByID(int id){
        for (Guest g : guests){
            if (g.getAccountNumber() == id){
                return g;
            }
        }

        return null;
    }
}
