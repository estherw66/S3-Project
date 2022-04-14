package com.fontys.s3itproject.business;

import com.fontys.s3itproject.models.Login;
import com.fontys.s3itproject.models.Person;

public interface LoginService {
    public Person checkLogin(Login l);
}
