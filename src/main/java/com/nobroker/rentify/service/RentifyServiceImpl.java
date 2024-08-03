package com.nobroker.rentify.service;

import com.nobroker.rentify.controller.dto.LoginRequest;
import com.nobroker.rentify.entity.User;
import com.nobroker.rentify.repository.LogInRepository;
import com.nobroker.rentify.repository.SignUpRepository;
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
    public void signUp(User userRequest) throws Exception {
        String email = userRequest.getEmail();
        Optional<User> existingUser = signUpRepository.findById(email);
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("User with this email already exists");
        }
        String hashedPassword = passwordEncoder.encode(userRequest.getPassword());
        userRequest.setPassword(hashedPassword);
        signUpRepository.save(userRequest);
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

