package com.example.user_service.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.user_service.dto.UserDto;
import com.example.user_service.model.User;
import com.example.user_service.service.UserService;
@RestController
@RequestMapping("/internal/users")
public class InternalUserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        User user = userService.getUserByUsername(username).get();
        if (user != null) {
            UserDto userDto = new UserDto(
                user.getId(),
                user.getUsername(),
                user.getPassword()
            );

            return ResponseEntity.ok(userDto);
        } else {
        	String errorMessage = "User with username '" + username + "' not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }
}
