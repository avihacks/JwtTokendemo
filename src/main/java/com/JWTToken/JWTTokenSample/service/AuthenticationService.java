package com.JWTToken.JWTTokenSample.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.JWTToken.JWTTokenSample.dto.LoginUserDto;
import com.JWTToken.JWTTokenSample.dto.RegisterUserDto;
import com.JWTToken.JWTTokenSample.entity.User;
import com.JWTToken.JWTTokenSample.repo.UserRepository;


@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    
    private final PasswordEncoder passwordEncoder;
    
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
        UserRepository userRepository,
        AuthenticationManager authenticationManager,
        PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUserDto input) {

    	
        User user = new User()
                .setFullName(input.getName())
                .setEmail(input.getEmail())
                .setPassword(passwordEncoder.encode(input.getPassword()));

        System.out.println("Saving user:");
    	System.out.println("Name: " + user.getFullName());
    	System.out.println("Email: " + user.getEmail());
    	System.out.println("Password: " + user.getPassword());

        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}
