/**
 * 
 */
package com.JWTToken.JWTTokenSample.entity;


import java.util.Collection;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
/**
 * 
 */
@Table(name = "users")
@Entity
public class User implements UserDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private Integer id;
	
    @Column(nullable = false)
	private String name;
    
    @Column(unique = true, length = 100, nullable = false)
	private String email;
    
    @Column(nullable = false)
	private String password;
    
    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
	private Date updatedAt;
	
	/**
	 * @return the id
	 */
    public Integer getId() {
        return id;
    }

    public User setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getFullName() {
        return name;
    }

    public User setFullName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public Date getCreatedAt() {
        return createAt;
    }

    public User setCreatedAt(Date createAt) {
        this.createAt = createAt;
        return this;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public User setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", createdAt=" + createAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

   
    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getPassword() {
        return password;
    }
}
	
