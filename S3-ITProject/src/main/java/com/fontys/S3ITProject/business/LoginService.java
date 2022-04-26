package com.fontys.s3itproject.business;

import com.fontys.s3itproject.entity.Login;
import com.fontys.s3itproject.entity.Person;
import org.springframework.stereotype.Service;

public interface LoginService {
    public Person checkLogin(Login l);
}
