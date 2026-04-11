package com.example.userService.service;

import com.example.userService.model.User;
import com.example.userService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public User addUser(User user) {
        return repo.save(user);
    }

    public User getUserById(Long id) {
        return repo.findById(id).orElse(null);
    }
}
