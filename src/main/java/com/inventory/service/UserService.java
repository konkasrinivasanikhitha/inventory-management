package com.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.dto.LoginRequest;
import com.inventory.dto.RegisterRequest;
import com.inventory.entity.User;
import com.inventory.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User register(RegisterRequest request) {

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole("USER");

        return userRepository.save(user);
    }

    public User login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail());

        if(user != null &&
           user.getPassword().equals(request.getPassword())) {
            return user;
        }

        return null;
    }

    public User getProfile(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User updateProfile(int id, User updatedUser) {

        User user = userRepository.findById(id).orElse(null);

        if(user != null) {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());

            return userRepository.save(user);
        }

        return null;
    }
}