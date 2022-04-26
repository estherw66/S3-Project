package com.fontys.s3itproject.business;

import com.fontys.s3itproject.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    public boolean createUser(User u);
    public List<User> readAllUsers();
    public User readUserByID(Long id);
    public boolean updateUser(User u);
    public boolean deleteUser(User u);
}
