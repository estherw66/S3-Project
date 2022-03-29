package com.fontys.S3ITProject.controller;

import com.fontys.S3ITProject.business.UserService;

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
}
