package com.nobroker.rentify.controller;

import com.nobroker.rentify.controller.dto.LoginRequest;
import com.nobroker.rentify.entity.User;
import com.nobroker.rentify.helper.JwtHelper;
import com.nobroker.rentify.service.RentifyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.nobroker.rentify.controller.dto.LoginResponse;

@Controller
@RequestMapping("/auth")
public class RentifyController {

    RentifyService rentifyService;
    AuthenticationManager authenticationManager;

    RentifyController(RentifyService rentifyService, AuthenticationManager authenticationManager){
        this.rentifyService = rentifyService;
        this.authenticationManager = authenticationManager;

    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User userRequest) {
        try {
            rentifyService.signUp(userRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("User signed up successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing your request");
        }
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        String token = JwtHelper.generateToken(request.getEmail());
        rentifyService.logIn(request);
        return ResponseEntity.ok(new LoginResponse(request.getEmail(), token));
    }


    @RequestMapping("/test")
    public ResponseEntity<Void> test() {
//        System.out.println("Inside Authentication Test");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    public ResponseEntity<Void> hello() {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
