package com.nnk.springboot.domain;

import com.nnk.springboot.AuthenticationProvider;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @NotEmpty(message = "Field cannot be empty")
    @Column(name="username")
    private String username;

    @NotEmpty(message = "Field cannot be empty")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[0-9])(?=.*?[&é\"'(-è_çà)=^$ù*,;:!]).{8,}$", message = "Password must contain at least one digit.")
    @Column(name="password")
    private String password;

    @NotEmpty(message = "Field cannot be empty")
    @Column(name="fullname")
    private String fullname;

    @NotEmpty(message = "Select a role")
    @Column(name="role")
    private String role;

    @Enumerated(EnumType.STRING)
    @Column(name="auth_provider")
    private AuthenticationProvider authProvider;

    public User() {
    }

    public User(String username, String password, String fullname, String role) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public AuthenticationProvider getAuthProvider() {
        return authProvider;
    }

    public void setAuthProvider(AuthenticationProvider authProvider) {
        this.authProvider = authProvider;
    }
}
