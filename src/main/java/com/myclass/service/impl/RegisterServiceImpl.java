package com.myclass.service.impl;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.myclass.dto.RegisterDto;
import com.myclass.entity.User;
import com.myclass.repository.RoleRepository;
import com.myclass.repository.UserRepository;
import com.myclass.service.RegisterService;
import com.myclass.validation.EmailExistsException;

@Service
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Transactional
	@Override
	public User registerNewUserAccount(RegisterDto accountDto) throws EmailExistsException {

		if (!emailExist(accountDto.getEmail())) {
			User user = new User();
			user.setPassword(accountDto.getPassword());
			user.setEmail(accountDto.getEmail());
			user.setRoleId(roleRepository.findFirstByName("ROLE_MEMBER").getId());
			String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
			user.setDelete(0);
			user.setPassword(hashed);
			try {

				String id = UUID.randomUUID().toString();
				user.setId(id);
				if (userRepository.save(user) != null) {
					return user;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public boolean emailExist(String email) {
		User user = userRepository.findByEmailAndIsDelete(email, 0);
		if (user != null) {
			return true;
		}
		return false;
	}
}
