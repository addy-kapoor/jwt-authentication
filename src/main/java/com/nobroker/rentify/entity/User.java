package com.nobroker.rentify.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

@Entity
@Table(name="signup")
public class User{

    @Column(name="uid")
    private String uid;

    @Id
    @Column(name="email")
    @NotNull(message = "Please provide a email")
    private String email;

    @Column(name="phoneno")
    @NotNull(message = "Please provide a phoneno")
    private String phoneNo;

    @Column(name="password")
    @NotNull(message = "Please provide a password")
    @Size(min = 2, message = "Password must be at least 8 characters long")
    private String password;

    @Column(name="name")
    @NotNull(message = "Please provide a fullName")
    @NotBlank(message = "Please provide a fullName")
    @NotEmpty(message = "Name may not be empty")
    private String fullName;

    User(){
        this.uid =  UUID.randomUUID().toString().replace("-", "").substring(0, 24);
    }

    public User(String email, String phoneNo, String password, String fullName) {
        this.email = email;
        this.phoneNo = phoneNo;
        this.password = password;
        this.fullName = fullName;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", email='" + email + '\'' +
                ", phoneNo=" + phoneNo +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
