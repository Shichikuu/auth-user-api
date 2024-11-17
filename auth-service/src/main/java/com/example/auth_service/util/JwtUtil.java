package com.example.auth_service.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

@Component
public class JwtUtil {
	@Value("${jwt.secret}")
    private String secretKey;

	public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Token validity: 10 hours
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // Sign the token using the secret key
                .compact();
    }
	
	private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes()); // Use the secret key for signing and validation
    }
	
    public String extractUsername(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    // Utility method to get all claims from the token
    private Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey()) // Set the signing key for validation
                .build()
                .parseClaimsJws(token) // Parse the claims from the token
                .getBody();
    }
    
    // Validate the token
    public Boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }
    
    
 // check if the token is expired
    private boolean isTokenExpired(String token) {
        return getClaimsFromToken(token).getExpiration().before(new Date());
    }


}
