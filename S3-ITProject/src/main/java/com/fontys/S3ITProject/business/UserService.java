package com.fontys.s3itproject.business;

import com.fontys.s3itproject.models.User;

import java.util.List;

public interface UserService {
    public boolean createUser(User u);
    public List<User> readAllUsers();
    public User readUserByID(int id);
    public boolean updateUser(User u);
    public boolean deleteUser(User u);
}
