package com.myclass.service.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.myclass.dto.LoginDto;
import com.myclass.service.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
	@Override
	public LoginDto getCurrentLoginAccount() {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			LoginDto curAccount = (LoginDto) principal;
			return curAccount;
		} else {
			System.out.println(principal.toString());
			return null;
		}

	}

}
