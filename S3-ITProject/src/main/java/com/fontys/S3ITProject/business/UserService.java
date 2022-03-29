package com.fontys.S3ITProject.business;

import com.fontys.S3ITProject.models.User;

import java.util.List;

public interface UserService {
    public boolean createUser(User u);
    public List<User> readAllUsers();
    public User readUserByID(int id);
    public boolean updateUser(User u);
    public boolean deleteUser(User u);
}
