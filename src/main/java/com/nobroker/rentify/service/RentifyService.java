package com.nobroker.rentify.service;

import com.nobroker.rentify.controller.dto.LoginRequest;
import com.nobroker.rentify.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface RentifyService {
    public ResponseEntity<String> signUp(User user) throws Exception;

    public void logIn(LoginRequest loginRequest);

}
