package com.nobroker.rentify.service;

import com.nobroker.rentify.controller.dto.LoginRequest;
import com.nobroker.rentify.entity.User;
import com.nobroker.rentify.repository.LogInRepository;
import com.nobroker.rentify.repository.SignUpRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RentifyServiceImpl implements RentifyService{

    private final SignUpRepository signUpRepository;

    private final PasswordEncoder passwordEncoder;

    private final LogInRepository logInRepository;

    private final UserDetailsServiceImpl userDetailsServiceImpl;

    public RentifyServiceImpl(SignUpRepository signUpRepository, PasswordEncoder passwordEncoder, LogInRepository logInRepository, UserDetailsServiceImpl userDetailsServiceImpl) {
        this.signUpRepository = signUpRepository;
        this.passwordEncoder = passwordEncoder;
        this.logInRepository = logInRepository;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @Override
    public ResponseEntity<String> signUp(User userRequest) {
        System.out.println("userRequest "+userRequest);
        String email = userRequest.getEmail();
        String phoneNo = userRequest.getPhoneNo();
        Optional<User> existingUser = signUpRepository.findById(email);
        Optional<User> existingUser2 = signUpRepository.findByPhoneNo(phoneNo);
        System.out.println("existingUser "+existingUser);
        System.out.println("existingUser2 "+existingUser2);
//        System.out.println("Sign up details of user " + userRequest);
        if (existingUser.isPresent()) {
            return new ResponseEntity<>("User with this email already exists", HttpStatus.BAD_REQUEST);
        }
        if(existingUser2.isPresent()){
            return new ResponseEntity<>("Phone number already registered", HttpStatus.BAD_REQUEST);
        }
        String hashedPassword = passwordEncoder.encode(userRequest.getPassword());
        userRequest.setPassword(hashedPassword);
        signUpRepository.save(userRequest);
        return new ResponseEntity<>("User signed up successfully", HttpStatus.CREATED);
    }

    @Override
    public void logIn(LoginRequest loginRequest) {
        Optional<User> userDetails = signUpRepository.findById(loginRequest.getEmail());
        if (userDetails.isPresent()){
            User user = userDetails.get();
            loginRequest.setSignup_id(user.getUid());
        }
        logInRepository.save(loginRequest);
    }
}

