package com.nobroker.rentify.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name="signup")
public class User{

    @Column(name="uid")
    private String uid;

    @Id
    @Column(name="email")
    private String email;

    @Column(name="phoneno")
    private int phoneNo;

    @Column(name="password")
    private String password;

    User(){
        this.uid =  UUID.randomUUID().toString().replace("-", "").substring(0, 24);
    }

    public User(String email, int phoneNo, String password) {
        this.email = email;
        this.phoneNo = phoneNo;
        this.password = password;
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

    public int getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(int phoneNo) {
        this.phoneNo = phoneNo;
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }

    public String getPassword() {
        return password;
    }

//    @Override
//    public String getUsername() {
//        return null;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return UserDetails.super.isAccountNonExpired();
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return UserDetails.super.isAccountNonLocked();
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return UserDetails.super.isCredentialsNonExpired();
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return UserDetails.super.isEnabled();
//    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "SignUp{" +
                "uid='" + uid + '\'' +
                ", email='" + email + '\'' +
                ", phoneNo=" + phoneNo +
                ", password='" + password + '\'' +
                '}';
    }
}
