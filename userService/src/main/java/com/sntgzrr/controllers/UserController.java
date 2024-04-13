package com.sntgzrr.controllers;

import com.sntgzrr.models.User;
import com.sntgzrr.services.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private final UserServiceImpl userService;
    @PostMapping
    public User createUser (@RequestBody User user){
        return this.userService.saveUser(user);
    }
}
