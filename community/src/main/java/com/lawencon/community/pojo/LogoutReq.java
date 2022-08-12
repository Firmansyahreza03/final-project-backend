package com.lawencon.community.pojo;

import javax.validation.constraints.NotBlank;

public class LogoutReq {
	@NotBlank(message = "Email can't be empty")
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
