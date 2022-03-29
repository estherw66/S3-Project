package com.fontys.S3ITProject.persistence;

import com.fontys.S3ITProject.models.Guest;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserRepository {
    public Guest checkLogin(String email, String password);
    public List<Guest> getAllUsers();
    public boolean createUser(Guest user);
}
