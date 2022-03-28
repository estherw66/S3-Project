package com.fontys.S3ITProject.persistence;

import com.fontys.S3ITProject.models.Employee;
import com.fontys.S3ITProject.models.Guest;
import com.fontys.S3ITProject.models.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository {

    public Person checkLogin(String email, String password);
    public Person getUserById(Person person);
}
