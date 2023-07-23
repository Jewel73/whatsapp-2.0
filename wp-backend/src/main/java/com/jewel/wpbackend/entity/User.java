package com.jewel.wpbackend.entity;

import java.util.List;

public class User {
	
	private long userId;
	private String email;
	private String password;
	
	private List<String> roles;
	
	

	public User(long userId, String email, String password, List<String> roles) {
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	

}
