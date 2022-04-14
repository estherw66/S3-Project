package com.fontys.s3itproject.persistence;

import com.fontys.s3itproject.models.Login;
import com.fontys.s3itproject.models.Person;

public interface LoginRepository {
    public Person checkLogin(Login l);
}
