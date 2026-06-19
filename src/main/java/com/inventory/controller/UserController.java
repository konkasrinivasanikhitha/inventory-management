package com.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.inventory.dto.LoginRequest;
import com.inventory.dto.RegisterRequest;
import com.inventory.entity.User;
import com.inventory.service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    @PostMapping("/login")
    public User login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }

    @GetMapping("/profile/{id}")
    public User getProfile(@PathVariable int id) {
        return userService.getProfile(id);
    }

    @PutMapping("/profile/{id}")
    public User updateProfile(@PathVariable int id,
                              @RequestBody User user) {
        return userService.updateProfile(id, user);
    }
}