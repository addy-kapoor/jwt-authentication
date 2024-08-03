package com.nobroker.rentify.controller.dto;

public class LoginResponse {
    String email;
    String token;

    public LoginResponse(String email, String token) {
        this.email = email;
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "email='" + email + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
