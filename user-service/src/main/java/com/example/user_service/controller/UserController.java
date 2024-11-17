package com.example.user_service.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.user_service.dto.ProfileDto;
import com.example.user_service.model.User;
import com.example.user_service.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
        // Check if username or email already exists
        if (userService.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }

        if (userService.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }

        userService.saveUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
    
    @PutMapping("/update/{username}")
    public ResponseEntity<?> updateUser(@PathVariable String username, @Valid @RequestBody ProfileDto profileDto, Authentication authentication) {
    	String currentUsername = authentication.getName();
    	if (!currentUsername.equals(username)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
        }
    	Optional<User> userOpt = userService.getUserByUsername(username);
    	if (userOpt.isPresent()) {
            User existingUser = userOpt.get();

            // Update allowed fields
            if(profileDto.getUsername() != null) {
            	if (userService.existsByUsername(profileDto.getUsername())) {
                    return ResponseEntity.badRequest().body("Error: Username is already taken!");
                }
            	existingUser.setUsername(profileDto.getUsername());
            }
            
            if(profileDto.getPassword() != null) {
            	existingUser.setPassword(profileDto.getPassword());
            }
            if(profileDto.getEmail() != null) {
            	if (userService.existsByEmail(profileDto.getEmail())) {
                    return ResponseEntity.badRequest().body("Error: Email is already in use!");
                }
            	existingUser.setEmail(profileDto.getEmail());
            }
            
            if(profileDto.getName() != null) {
            	existingUser.setName(profileDto.getName());
            }
            
            if(profileDto.getAge() != null) {
            	existingUser.setAge(profileDto.getAge());
            }

            User updatedUser = userService.saveUser(existingUser);


            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    	
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
    	Optional<User> userOpt = userService.getUserByUsername(username);
    	if (userOpt.isPresent()) {
            User existingUser = userOpt.get();
            return ResponseEntity.ok(existingUser);
    	} else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
    
}
