package com.fontys.s3itproject.persistence.impl;

import com.fontys.s3itproject.entity.User;
import com.fontys.s3itproject.persistence.UserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final RepositoryImpl repository;

    public UserRepositoryImpl(RepositoryImpl repository) {
        this.repository = repository;
    }

    @Override
    public boolean createUser(User u) {
        if (readUserByID(u.getId()) != null){
            repository.users.add(u);
            return true;
        }

        return false;
    }

    @Override
    public List<User> readAllUsers() {
        return repository.users;
    }

    @Override
    public User readUserByID(Long id) {
        for (User user : repository.users){
            if (user.getId() == id){
                return user;
            }
        }

        return null;
    }

    @Override
    public boolean updateUser(User u) {
        return false;
    }

    @Override
    public boolean deleteUser(User u) {
        if (readUserByID(u.getId()) != null){
            repository.users.remove(u);
            return true;
        }

        return false;
    }
}
