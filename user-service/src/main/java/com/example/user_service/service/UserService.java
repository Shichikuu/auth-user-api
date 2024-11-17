package com.example.user_service.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.user_service.model.User;
import com.example.user_service.repository.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepository;
	private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User saveUser(User user) {
    	user.setPassword(passwordEncoder.encode(user.getPassword()));
    	userRepository.save(user);
        return user;
    }

	public Optional<User> getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	// Check if username exists
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    // Check if email exists
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

}
