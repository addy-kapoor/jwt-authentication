package com.nobroker.rentify.repository;

import com.nobroker.rentify.controller.dto.LoginRequest;
import com.nobroker.rentify.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogInRepository extends JpaRepository<LoginRequest, String> {
}
