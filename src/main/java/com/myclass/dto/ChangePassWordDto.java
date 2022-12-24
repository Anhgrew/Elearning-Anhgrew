package com.myclass.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.myclass.validation.ValidPassword;

public class ChangePassWordDto {
	private String id;

	private String email;

	@NotNull
	@NotEmpty
	@ValidPassword
	private String password;

	@NotNull
	@NotEmpty
	@ValidPassword
	private String newPassWord;

	@NotNull
	@NotEmpty
	@ValidPassword
	private String confirm;

	public ChangePassWordDto() {
		super();
	}

	public ChangePassWordDto(String id, String email, @NotNull @NotEmpty String password,
			@NotNull @NotEmpty String newPassWord, @NotNull @NotEmpty String confirm) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.newPassWord = newPassWord;
		this.confirm = confirm;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getNewPassWord() {
		return newPassWord;
	}

	public void setNewPassWord(String newPassWord) {
		this.newPassWord = newPassWord;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

}
