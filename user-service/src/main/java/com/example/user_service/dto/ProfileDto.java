package com.example.user_service.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProfileDto {
    
	@Size(min = 3, max = 50)
    @Column(unique = true, nullable = false)
    private String username;
	
	@Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
    
    @Email(message = "Invalid email format")
    @Column(unique = true, nullable = false)
    private String email;
    
    @Size(min = 3, max = 50, message = "Name must be between 3 to 50 characters")
    private String name;
    
    @Positive(message = "Age is not valid")
    private Integer age;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
    
    
}
