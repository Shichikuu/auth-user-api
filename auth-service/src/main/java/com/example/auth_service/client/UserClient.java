package com.example.auth_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.auth_service.config.FeignClientConfig;
import com.example.auth_service.dto.UserDto;

@FeignClient(name = "user-service", url = "http://localhost:8081", configuration = FeignClientConfig.class)
public interface UserClient {

    @GetMapping("/internal/users/{username}")
    UserDto getUserByUsername(@PathVariable("username") String username);
}
