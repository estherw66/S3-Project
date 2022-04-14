package com.fontys.s3itproject.persistence;

import com.fontys.s3itproject.models.User;

import java.util.List;

public interface UserRepository {
    public boolean createUser(User u);
    public List<User> readAllUsers();
    public User readUserByID(int id);
    public boolean updateUser(User u);
    public boolean deleteUser(User u);
}
