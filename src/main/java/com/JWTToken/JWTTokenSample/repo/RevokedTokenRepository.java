package com.JWTToken.JWTTokenSample.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JWTToken.JWTTokenSample.entity.RevokedToken;

public interface RevokedTokenRepository extends JpaRepository<RevokedToken, String> {
    boolean existsByToken(String token);
}