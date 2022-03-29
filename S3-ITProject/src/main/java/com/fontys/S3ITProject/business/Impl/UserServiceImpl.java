package com.fontys.S3ITProject.business.Impl;

import com.fontys.S3ITProject.business.UserService;
import com.fontys.S3ITProject.models.User;
import com.fontys.S3ITProject.persistence.UserRepository;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;

    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public boolean createUser(User u) {
        return false;
    }

    @Override
    public List<User> readAllUsers() {
        return null;
    }

    @Override
    public User readUserByID(int id) {
        return null;
    }

    @Override
    public boolean updateUser(User u) {
        return false;
    }

    @Override
    public boolean deleteUser(User u) {
        return false;
    }
}
