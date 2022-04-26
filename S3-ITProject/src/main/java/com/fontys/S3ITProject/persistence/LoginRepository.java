package com.fontys.s3itproject.persistence;

import com.fontys.s3itproject.entity.Login;
import com.fontys.s3itproject.entity.Person;
import org.springframework.stereotype.Repository;

public interface LoginRepository {
    public Person checkLogin(Login l);
}
