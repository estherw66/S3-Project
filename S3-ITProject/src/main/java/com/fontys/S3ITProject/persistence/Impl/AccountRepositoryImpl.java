package com.fontys.S3ITProject.persistence.Impl;

import com.fontys.S3ITProject.models.*;
import com.fontys.S3ITProject.persistence.AccountRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

    private final List<Person> users = new ArrayList<>();

    public AccountRepositoryImpl(){
        // create users
        users.add(new Employee(1,"Esther", "Wolfs", "e.wolfs@goldskye.com", "password", new Address("Mozartlaan 41", "5151KA", "Drunen"), "+31612901749", LocalDate.of(1998,01,01)));
        users.add(new Employee(2,"Emma", "Wiles", "e.wiles@goldskye.com", "password", new Address("Leidsestraat 41", "4864AD", "Amsterdam"), "+31678453321", LocalDate.of(1999, 04, 19)));
        users.add(new Employee(3,"Jessy", "Avery", "j.avery@goldskye.com", "password", new Address("Kalverstraat 12", "4784AE", "Amsterdam"), "+31612783584", LocalDate.of(1998,01, 06)));
        users.add(new Employee(4,"David", "Davis", "d.davis@goldskye.com", "password", new Address("Ooststraat 7", "7845JG", "Amsterdam"), "+31645886636", LocalDate.of(1995, 6, 30)));
        users.add(new Employee(5,"Kate", "Price", "k.price@skyegold.com", "password", new Address("Coates Gardens 2", "4571IE", "Zaandam"), "+31644547382", LocalDate.of(1973, 4, 10)));

        users.add(new Guest(1,"John", "Floreani", "jflor@gmail.com", "password", new CreditCard("4780 0054 4578", "J. Floreani", "04/23")));
        users.add(new Guest(2,"Jan", "Pieters", "janpieters@gmail.com", "password", null));
        users.add(new Guest(3,"Henk", "Jansen", "henkjansen@hotmail.com", "password", null));
    }

    @Override
    public Person checkLogin(String email, String password) {
        for (Person person : users){
            if (person.getEmail().equals(email) && person.getPassword().equals(password)){
                return person;
            }
        }

        return null;
    }

    @Override
    public List<Person> getAllUsers() {
        return users;
    }


}
