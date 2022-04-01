package com.fontys.S3ITProject.business.Impl;

import com.fontys.S3ITProject.business.UserService;
import com.fontys.S3ITProject.models.User;
import com.fontys.S3ITProject.persistence.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;

    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public boolean createUser(User u) {
        return userRepo.createUser(u);
    }

    @Override
    public List<User> readAllUsers() {
        return userRepo.readAllUsers();
    }

    @Override
    public User readUserByID(int id) {
        return userRepo.readUserByID(id);
    }

    @Override
    public boolean updateUser(User u) {
        return userRepo.updateUser(u);
    }

    @Override
    public boolean deleteUser(User u) {
        return userRepo.deleteUser(u);
    }
}
