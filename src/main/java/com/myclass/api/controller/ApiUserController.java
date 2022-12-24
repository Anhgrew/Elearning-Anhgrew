package com.myclass.api.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.myclass.entity.User;
import com.myclass.service.UserService;

@RestController
@RequestMapping("/api/user")
public class ApiUserController {

	@Autowired
	private UserService userService;

	@GetMapping("/index")
	public Object getAll() {
		List<User> roles = userService.findAll();
		if (roles.isEmpty()) {
			return new ResponseEntity<String>("No content!", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(roles, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public Object getOne(@PathVariable("id") String id) {
		User entity = userService.findById(id).get();
		if (entity != null) {
			return new ResponseEntity<User>(entity, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Not found entity!", HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("/add")
	public Object addRole(@Valid @RequestBody User user, BindingResult errors) {
		boolean created = userService.add(user);
		if (errors.hasErrors()) {
			return new ResponseEntity<Object>(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		if (created) {
			return new ResponseEntity<String>("Successfully added!", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Unsuccessfully added!", HttpStatus.EXPECTATION_FAILED);
		}
	}

	@PutMapping("/update/{id}")
	public Object updateCategory(@PathVariable("id") String id, @Valid @RequestBody User user, BindingResult errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity<Object>(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		} else {
			User entity = userService.findById(id).get();
			if (entity == null) {
				return new ResponseEntity<String>("Not found entity!", HttpStatus.NOT_FOUND);
			} else {
				boolean updated = userService.update(entity);
				if (updated) {
					return new ResponseEntity<String>("Successfully updated!", HttpStatus.OK);
				} else {
					return new ResponseEntity<String>("Update failed!", HttpStatus.EXPECTATION_FAILED);
				}
			}

		}

	}

	@DeleteMapping("/delete/{id}")
	public Object deleteCategory(@PathVariable("id") String id) {
		User entity = userService.findById(id).get();
		if (entity != null) {
			userService.removeById(id);
			return new ResponseEntity<String>("Successully deleted!", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Not found entity!", HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "/uploadAvatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Object> uploadAvatar(@RequestParam("id") String id,
			@RequestPart("file") MultipartFile fileUploaded) throws IOException {
		return userService.setImage(fileUploaded, id);
	}

	@GetMapping(value = "/getAvatar", produces = "image/*")
	public Object getAvatar(@RequestParam("id") String id) {
		return userService.getImage(id);
	}

}
