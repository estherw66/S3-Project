package com.fontys.S3ITProject.business;

import com.fontys.S3ITProject.models.Person;

public interface LoginService {
    public Person checkLogin(String email, String password);
}
