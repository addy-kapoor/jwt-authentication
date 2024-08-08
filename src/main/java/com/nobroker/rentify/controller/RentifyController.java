package com.nobroker.rentify.controller;

import com.nobroker.rentify.controller.dto.LoginRequest;
import com.nobroker.rentify.entity.User;
import com.nobroker.rentify.helper.JwtHelper;
import com.nobroker.rentify.service.RentifyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.nobroker.rentify.controller.dto.LoginResponse;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/auth")
@CrossOrigin("http://192.168.1.4:8081")
public class RentifyController {

    RentifyService rentifyService;
    AuthenticationManager authenticationManager;

    RentifyController(RentifyService rentifyService, AuthenticationManager authenticationManager){
        this.rentifyService = rentifyService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody User userRequest, BindingResult br) {
        System.out.println("inside SignUp function controller "+userRequest);
        if(br.hasErrors()) {
            System.out.println("Binding Result Error "+br.getAllErrors());
            return ResponseEntity.badRequest().body("Validation failed");
        }
        try {
            return rentifyService.signUp(userRequest);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
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
//      System.out.println("Inside Authentication Test");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    public ResponseEntity<Void> hello() {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
