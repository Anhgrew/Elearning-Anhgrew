package com.myclass.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.myclass.validation.ValidEmail;
import com.myclass.validation.ValidPassword;

public class RegisterDto {
	@ValidEmail
	@NotBlank
	@Email
	private String email;

	@NotNull
	@NotEmpty
	@ValidPassword
	private String password;

	public RegisterDto(@NotNull @NotEmpty String email, @NotNull @NotEmpty String password, String confirm) {
		super();

		this.email = email;
		this.password = password;
		this.confirm = confirm;
	}

	public RegisterDto() {
		super();
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

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	private String confirm;

}
