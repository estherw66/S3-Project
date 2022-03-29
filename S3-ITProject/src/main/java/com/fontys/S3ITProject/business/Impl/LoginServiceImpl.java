package com.fontys.S3ITProject.business.Impl;

import com.fontys.S3ITProject.business.LoginService;
import com.fontys.S3ITProject.models.Guest;
import com.fontys.S3ITProject.persistence.AccountRepository;
import com.fontys.S3ITProject.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;

    @Autowired
    public LoginServiceImpl(@Qualifier("userRepo") UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Guest checkLogin(String email, String password) {
        return userRepository.checkLogin(email, password);
    }

    @Override
    public List<Guest> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public boolean createUser(Guest user) {
        return userRepository.createUser(user);
    }
}