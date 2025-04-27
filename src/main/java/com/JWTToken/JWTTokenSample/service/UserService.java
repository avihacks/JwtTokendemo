package com.JWTToken.JWTTokenSample.service;

import org.springframework.stereotype.Service;

import com.JWTToken.JWTTokenSample.entity.User;
import com.JWTToken.JWTTokenSample.repo.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);

        return users;
    }
}