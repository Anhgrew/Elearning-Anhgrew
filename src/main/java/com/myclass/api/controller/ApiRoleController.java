package com.myclass.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.entity.Role;
import com.myclass.service.RoleService;

@RestController
@RequestMapping("/api/role")
public class ApiRoleController {

	@Autowired
	private RoleService roleService;

	@GetMapping("/index")
	public Object getAll() {
		List<Role> roles = roleService.findAll();
		if (roles.isEmpty()) {
			return new ResponseEntity<String>("No content!", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Role>>(roles, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public Object getOne(@PathVariable("id") String id) {
		Role entity = roleService.findById(id).get();
		if (entity != null) {
			return new ResponseEntity<Role>(entity, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Not found entity!", HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("/add")
	public Object addRole(@Valid @RequestBody Role role, BindingResult errors) {
		boolean created = roleService.add(role);
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
	public Object updateCategory(@PathVariable("id") String id, @Valid @RequestBody Role role, BindingResult errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity<Object>(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		} else {
			Role entity = roleService.findById(id).get();
			if (entity == null) {
				return new ResponseEntity<String>("Not found entity!", HttpStatus.NOT_FOUND);
			} else {
				boolean updated = roleService.update(entity);
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
		Role entity = roleService.findById(id).get();
		if (entity != null) {
			roleService.removeById(id);
			return new ResponseEntity<String>("Successully deleted!", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Not found entity!", HttpStatus.NOT_FOUND);
		}
	}

}
