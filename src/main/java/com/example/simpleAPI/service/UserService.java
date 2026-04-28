package com.example.simpleAPI.service;


import com.example.simpleAPI.models.User;
import com.example.simpleAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userRepository.delete(user);
        return user;
    }

    public User updateUser(Long userId, User newUser) {
        User existing = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (newUser.getUserName() != null) {
            existing.setUserName(newUser.getUserName());
        }

        if (newUser.getEmail() != null) {
            existing.setEmail(newUser.getEmail());
        }

        return userRepository.save(existing);
    }



}

