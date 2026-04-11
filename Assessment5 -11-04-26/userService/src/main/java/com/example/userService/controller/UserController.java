package com.example.userService.controller;

import com.example.userService.model.User;
import com.example.userService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public User addUser(@RequestBody User user) {
        return service.addUser(user);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return service.getUserById(id);
    }
}
