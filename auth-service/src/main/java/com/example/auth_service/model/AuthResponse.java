package com.example.auth_service.model;

public class AuthResponse {
	private String jwt;
	private String username;

	public AuthResponse(String jwt, String username) {
		super();
		this.jwt = jwt;
		this.username = username;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
