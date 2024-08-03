package com.nobroker.rentify.controller.dto;

import jakarta.persistence.*;

@Entity
@Table(name="login")
public class LoginRequest {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name = "signup_id")
    private String signup_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSignup_id() {
        return signup_id;
    }

    public void setSignup_id(String signup_id) {
        this.signup_id = signup_id;
    }

    public LoginRequest() {
    }

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
