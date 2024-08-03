package com.nobroker.rentify.service;

import com.nobroker.rentify.entity.User;
import com.nobroker.rentify.repository.SignUpRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final SignUpRepository repository;

    public UserDetailsServiceImpl(SignUpRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = repository.findById(email).orElseThrow(() -> new UsernameNotFoundException(String.format("User does not exist, email: %s", email)));
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
