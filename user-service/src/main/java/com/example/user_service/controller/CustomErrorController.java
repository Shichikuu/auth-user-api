package com.example.user_service.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
public class CustomErrorController implements ErrorController {

    @GetMapping("/error")
    public ResponseEntity<String> handleError() {
        return ResponseEntity.status(401).body("Custom Error: You are not authorized to access this resource.");
    }

    public String getErrorPath() {
        return "/error";
    }
}
