package com.fontys.s3itproject.business.impl;

import com.fontys.s3itproject.business.LoginService;
import com.fontys.s3itproject.entity.Login;
import com.fontys.s3itproject.entity.Person;
import com.fontys.s3itproject.persistence.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@RequiredArgsConstructor
@Service
public class LoginServiceImpl implements LoginService {
    private final LoginRepository loginRepo;

    @Override
    public Person checkLogin(Login l) {
        return loginRepo.checkLogin(l);
    }
}
