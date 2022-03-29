package com.fontys.S3ITProject.persistence.Impl;

import com.fontys.S3ITProject.models.Guest;
import com.fontys.S3ITProject.persistence.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("userRepo")
public class UserRepositoryImpl implements UserRepository {

    private final List<Guest> users = new ArrayList<>();

    public UserRepositoryImpl(){
        users.add(new Guest(1, "John", "Doe", "j.doe@mail.com", "password"));
        users.add(new Guest(2, "John", "Floreani", "j.flor@mail.com", "password"));
        users.add(new Guest(3, "Tim", "van Driel", "timmy@mail.com", "password" ));
        users.add(new Guest(4, "Tini", "Plant", "tiniplant@mail.com", "password" ));
        users.add(new Guest(5, "Karin", "Doe", "k.doe@mail.com", "password"));
    }

    @Override
    public Guest checkLogin(String email, String password) {
        for (Guest guest : users){
            if (guest.getEmail().equals("test") && guest.getPassword().equals("password")){
                return guest;
            }
        }

        return new Guest(10, "test", "test", "test", "test");
    }

    @Override
    public List<Guest> getAllUsers() {
        return this.users;
    }

    @Override
    public boolean createUser(Guest user) {
        this.users.add(user);

        return true;
    }
}
