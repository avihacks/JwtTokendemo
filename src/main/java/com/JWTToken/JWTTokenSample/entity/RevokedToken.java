package com.JWTToken.JWTTokenSample.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RevokedToken {

    @Id
    private String token;  // Token string will be the primary key
}