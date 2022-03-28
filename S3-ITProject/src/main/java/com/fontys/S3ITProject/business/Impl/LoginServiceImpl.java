package com.fontys.S3ITProject.business.Impl;

import com.fontys.S3ITProject.business.LoginService;
import com.fontys.S3ITProject.models.Person;
import com.fontys.S3ITProject.persistence.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    private final AccountRepository accountRepository;

    @Autowired
    public LoginServiceImpl(@Qualifier("loginDB") AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public Person checkLogin(String email, String password) {
        return accountRepository.checkLogin(email, password);
    }
}