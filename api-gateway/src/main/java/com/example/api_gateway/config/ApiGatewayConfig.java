package com.example.api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user_service", r -> r.path("/users/**")
                        .uri("http://localhost:8081"))  // URL of user service
		        .route("auth_service", r -> r.path("/auth/**")
		                .uri("http://localhost:8082"))  // URL of auth service
		        .build();
    }
}
