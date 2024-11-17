package com.example.auth_service.security;

import org.springframework.security.core.userdetails.UserDetails;

import com.example.auth_service.dto.UserDto;

import java.util.*;

import org.springframework.security.core.GrantedAuthority;

public class CustomUserDetails implements UserDetails {

    private UserDto user;

    public CustomUserDetails(UserDto user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    	return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}
