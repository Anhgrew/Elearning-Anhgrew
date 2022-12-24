package com.myclass.service;

import java.util.List;

import com.myclass.dto.ChangePassWordDto;
import com.myclass.dto.UserDto;
import com.myclass.entity.User;

public interface UserService extends GenericService<User, String> {
	List<User> listMember(int courseId);

	void updateDetail(UserDto user);

	void updatePicture(UserDto user);

	void updateSecurity(ChangePassWordDto user);
	
	void updatePassword(String password, String userId);

	User findByEmailAndDelete(String email);
}
