package com.myclass.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.myclass.dto.ChangePassWordDto;
import com.myclass.dto.UserDto;
import com.myclass.entity.User;
import com.myclass.entity.UserCourse;
import com.myclass.repository.UserRepository;
import com.myclass.service.UserCourseService;
import com.myclass.service.UserService;

@Service
public class UserServiceImpl extends GenericServiceImpl<User, String> implements UserService {

	private final String SRC = "D:/upload/image/category/";
	@Autowired
	protected UserRepository userRepository;

	@Autowired
	protected UserCourseService userCourseService;

	@Override
	public List<User> findAll() {
		return userRepository.findByIsDelete(0);
	}

	@Override
	public boolean add(User entity) {
		String hashed = BCrypt.hashpw(entity.getPassword(), BCrypt.gensalt(12));
		System.out.println("password encoding: " + hashed);
		entity.setDelete(0);
		entity.setPassword(hashed);
		try {

			String id = UUID.randomUUID().toString();
			entity.setId(id);
			if (userRepository.save(entity) != null) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<User> findAllByKeyWord(String keyword) {
		return userRepository.findAllByEmail(keyword, 0);
	}

	@Override
	public void removeById(String id) {
		User user = userRepository.findById(id).get();
		user.setDelete(1);
		userRepository.saveAndFlush(user);
	}

	@Override
	public Optional<User> findById(String id) {
		Optional<User> user = userRepository.findById(id);
		if (user.get().isDelete() == 0) {
			return user;
		}
		return null;
	}

	@Override
	public List<User> listMember(int courseId) {
		List<User> users = new LinkedList<User>();
		List<UserCourse> tmpList = userCourseService.findByCourseId(courseId);
		for (UserCourse item : tmpList) {
			User user = userRepository.findById(item.getUserId()).get();
			if (user != null && user.isDelete() == 0) {
				users.add(user);
			}
		}
		return users;
	}

	@Override
	public void updateDetail(UserDto user) {
		userRepository.updateUserDetail(user.getAddress(), user.getFacebook(), user.getFullname(), user.getPhone(),
				user.getWebsite(), user.getId());

	}

	@Override
	public void updatePicture(UserDto user) {
		userRepository.updatePicture(user.getAvatar(), user.getId());

	}

	@Override
	public User findByEmailAndDelete(String email) {
		return userRepository.findByEmailAndIsDelete(email, 0);
	}

	@Override
	public void updateSecurity(ChangePassWordDto user) {
		String hashed = BCrypt.hashpw(user.getNewPassWord(), BCrypt.gensalt(12));
		userRepository.updateSecurity(hashed, user.getId());
	}

	@Override
	@Transactional
	public ResponseEntity<Object> setImage(MultipartFile fileUploaded, String id) {
		if (fileUploaded != null || id != null) {
			String type = fileUploaded.getContentType().replace("image/", "");
			File data = new File(SRC + id + "." + type);
			String path = id + "." + type;
			try {
				data.createNewFile();
				FileOutputStream fos = new FileOutputStream(data);
				fos.write(fileUploaded.getBytes());
				userRepository.setAvatar(path, id);
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Object>("Unknown errors!", HttpStatus.EXPECTATION_FAILED);
			}
			return new ResponseEntity<Object>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>("Failed data!", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<InputStreamResource> getImage(String id) {
		if (id != null) {
			String pathDefault = "007.png";
			String path = userRepository.getAvatar(id);
			File data = null;
			if (path != null) {
				data = new File(SRC + path);
			} else {
				data = new File(SRC + pathDefault);
			}
			if (data.exists()) {
				try {
					InputStream is = new FileInputStream(data);
					return ResponseEntity.ok().contentLength(data.length()).contentType(MediaType.IMAGE_JPEG)
							.contentType(MediaType.IMAGE_PNG).body(new InputStreamResource(is));
				} catch (Exception e) {
					e.printStackTrace();
					return new ResponseEntity<InputStreamResource>(HttpStatus.EXPECTATION_FAILED);
				}

			} else {
				return new ResponseEntity<InputStreamResource>(HttpStatus.EXPECTATION_FAILED);
			}
		} else {
			return new ResponseEntity<InputStreamResource>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public void updatePassword(String password, String userId) {
		userRepository.updatePassword(password, userId);
	}

}
