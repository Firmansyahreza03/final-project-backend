package com.lawencon.community.pojo;

public class LoginReq {
	private String email;
	private String password;
	
	public void setEmail(String username) {
		this.email = username;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
}
