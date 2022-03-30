package com.fontys.S3ITProject.persistence;

import com.fontys.S3ITProject.models.Login;
import com.fontys.S3ITProject.models.Person;

public interface LoginRepository {
    public Person checkLogin(Login l);
}
