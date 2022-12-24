package com.myclass.service;

import com.myclass.dto.RegisterDto;
import com.myclass.entity.User;
import com.myclass.validation.EmailExistsException;

public interface RegisterService {
	User registerNewUserAccount(RegisterDto accountDto) throws EmailExistsException;

	public boolean emailExist(String email);
}
