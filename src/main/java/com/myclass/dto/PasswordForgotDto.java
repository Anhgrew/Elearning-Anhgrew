package com.myclass.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class PasswordForgotDto {

	@Email
	@NotEmpty
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public PasswordForgotDto(@Email @NotEmpty String email) {
		super();
		this.email = email;
	}

	public PasswordForgotDto() {
		super();
	}
}