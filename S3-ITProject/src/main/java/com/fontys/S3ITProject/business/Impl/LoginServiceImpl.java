package com.fontys.S3ITProject.business.Impl;

import com.fontys.S3ITProject.business.LoginService;
import com.fontys.S3ITProject.models.Login;
import com.fontys.S3ITProject.models.Person;
import com.fontys.S3ITProject.persistence.LoginRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    private final LoginRepository loginRepo;

    public LoginServiceImpl(LoginRepository loginRepo) {
        this.loginRepo = loginRepo;
    }

    @Override
    public Person checkLogin(Login l) {
        return null;
    }
}
