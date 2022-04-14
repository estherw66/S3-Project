package com.fontys.s3itproject.business.impl;

import com.fontys.s3itproject.business.LoginService;
import com.fontys.s3itproject.models.Login;
import com.fontys.s3itproject.models.Person;
import com.fontys.s3itproject.persistence.LoginRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    private final LoginRepository loginRepo;

    public LoginServiceImpl(LoginRepository loginRepo) {
        this.loginRepo = loginRepo;
    }

    @Override
    public Person checkLogin(Login l) {
        return loginRepo.checkLogin(l);
    }
}
