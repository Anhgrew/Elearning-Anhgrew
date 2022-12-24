package com.myclass.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.myclass.dto.LoginDto;
import com.myclass.entity.User;
import com.myclass.repository.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	// Kiem tra email
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmailAndIsDelete(email, 0);
		// Email sai
		if (user == null) {
			throw new UsernameNotFoundException("Email không tồn tại!");
		}
		// Join bang role lay ten
		String roleNameString = user.getRole().getName();
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(roleNameString));
		LoginDto loginDto = new LoginDto(user.getEmail(), user.getPassword(), authorities, user.getId(),
				user.getAvatar(), user.getFullname());
		return loginDto;
	}

}
