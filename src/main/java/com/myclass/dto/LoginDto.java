package com.myclass.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class LoginDto extends User implements UserDetails {

	private final String id;
	private static final long serialVersionUID = 1L;
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "LoginDto [id=" + id + ", email=" + email + ", fullname=" + fullname + ", avatar=" + avatar + "]";
	}

	private String fullname;
	private String avatar;

	public LoginDto(String username, String password, Collection<? extends GrantedAuthority> authorities, String id,
			String avatar, String fullname) {
		super(username, password, authorities);
		this.id = id;
		this.email = username;
		this.avatar = avatar;
		this.fullname = fullname;
	}

	public String getId() {
		return id;
	}

	public LoginDto(String username, String password, Collection<? extends GrantedAuthority> authorities, String id,
			String email, String fullname, String avatar) {
		super(username, password, authorities);
		this.id = id;
		this.email = email;
		this.fullname = fullname;
		this.avatar = avatar;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

}
