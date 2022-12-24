package com.myclass.dto;

import javax.validation.constraints.NotEmpty;

import com.myclass.validation.FieldMatch;
import com.myclass.validation.ValidPassword;

@FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match")
public class PasswordResetDto {

	@NotEmpty
	@ValidPassword
	private String password;

	@NotEmpty
	@ValidPassword
	private String confirmPassword;

	@NotEmpty
	private String token;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}