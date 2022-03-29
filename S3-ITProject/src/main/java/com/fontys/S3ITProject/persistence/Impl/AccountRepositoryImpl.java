package com.fontys.S3ITProject.persistence.Impl;

import com.fontys.S3ITProject.models.*;
import com.fontys.S3ITProject.persistence.AccountRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository("loginDB")
public class AccountRepositoryImpl implements AccountRepository {

    private final List<Person> users = new ArrayList<>();

    public AccountRepositoryImpl(){
        // create users
        users.add(new Guest(1,"John", "Floreani", "jflor@gmail.com", "password"));
        users.add(new Guest(2,"Jan", "Pieters", "janpieters@gmail.com", "password"));
        users.add(new Guest(3,"Henk", "Jansen", "henkjansen@hotmail.com", "password"));
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
    public Person getUserById(Person person) {
        return null;
    }
}
