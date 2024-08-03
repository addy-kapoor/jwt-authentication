package com.nobroker.rentify.repository;


import com.nobroker.rentify.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SignUpRepository extends JpaRepository<User, String> {

}

