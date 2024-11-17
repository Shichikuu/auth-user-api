package com.example.auth_service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import com.example.auth_service.client.UserClient;
import com.example.auth_service.dto.UserDto;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserClient userClient;

    @Autowired
    public CustomUserDetailsService(UserClient userClient) {
        this.userClient = userClient;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserDto userDto = userClient.getUserByUsername(username);
            return new CustomUserDetails(userDto);
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
