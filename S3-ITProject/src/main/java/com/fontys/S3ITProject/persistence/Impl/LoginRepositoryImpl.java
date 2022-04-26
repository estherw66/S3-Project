package com.fontys.s3itproject.persistence.impl;

import com.fontys.s3itproject.entity.Login;
import com.fontys.s3itproject.entity.Person;
import com.fontys.s3itproject.persistence.LoginRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
public class LoginRepositoryImpl implements LoginRepository {

    private final RepositoryImpl repository;

    public LoginRepositoryImpl(RepositoryImpl repository) {
        this.repository = repository;
    }

    @Override
    public Person checkLogin(Login l) {
        for (Person person : repository.users){
            if (person.getUsername().equals(l.getUsername()) && person.getPassword().equals(l.getPassword())){
                return person;
            }
        }

        for (Person person : repository.employees){
            if (person.getUsername().equals(l.getUsername()) && person.getPassword().equals(l.getPassword())){
                return person;
            }
        }

        return null;
    }
}
