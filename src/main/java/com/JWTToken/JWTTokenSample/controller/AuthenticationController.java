package com.JWTToken.JWTTokenSample.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JWTToken.JWTTokenSample.dto.LoginUserDto;
import com.JWTToken.JWTTokenSample.dto.RegisterUserDto;
import com.JWTToken.JWTTokenSample.entity.RevokedToken;
import com.JWTToken.JWTTokenSample.entity.User;
import com.JWTToken.JWTTokenSample.repo.RevokedTokenRepository;
import com.JWTToken.JWTTokenSample.repo.UserRepository;
import com.JWTToken.JWTTokenSample.responses.LoginResponse;
import com.JWTToken.JWTTokenSample.service.AuthenticationService;
import com.JWTToken.JWTTokenSample.service.JwtService;

import jakarta.servlet.http.HttpServletRequest;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    
    private final AuthenticationService authenticationService;
    private final RevokedTokenRepository revokedTokenRepository;


    public AuthenticationController(JwtService jwtService, 
    		AuthenticationService authenticationService,
    		RevokedTokenRepository revokedTokenRepository) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.revokedTokenRepository= revokedTokenRepository;
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return "No token found in request.";
        }

        String token = authHeader.substring(7);

        // Save token to revoked list
        revokedTokenRepository.save(new RevokedToken(token));

        return "Logout successful. Token revoked.";
    }
    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
    
    @GetMapping("/name")
    public String name_avi() {
		return "Avinash";
	}
    
    @GetMapping("/api/authorize")
    public boolean isUserLoggedIn() {
        boolean isLoggedIn = false;
        try {
            SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            isLoggedIn = true;
        } catch (Exception e) {
            isLoggedIn = false;
        }
        return isLoggedIn;
    }
    
}