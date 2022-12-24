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

import com.myclass.entity.Target;
import com.myclass.service.TargetService;

@RestController
@RequestMapping("/api/target")
public class ApiTargetController {

	@Autowired
	private TargetService targetService;

	@GetMapping("/index")
	public Object getAll() {
		List<Target> targets = targetService.findAll();
		if (targets.isEmpty()) {
			return new ResponseEntity<String>("No content!", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Target>>(targets, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public Object getOne(@PathVariable("id") int id) {
		Target entity = targetService.findById(id).get();
		if (entity != null) {
			return new ResponseEntity<Target>(entity, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Not found entity!", HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("/add")
	public Object addRole(@Valid @RequestBody Target target, BindingResult errors) {
		boolean created = targetService.add(target);
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
	public Object updateCategory(@PathVariable("id") int id, @Valid @RequestBody Target target, BindingResult errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity<Object>(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		} else {
			Target entity = targetService.findById(id).get();
			if (entity == null) {
				return new ResponseEntity<String>("Not found entity!", HttpStatus.NOT_FOUND);
			} else {
				boolean updated = targetService.update(entity);
				if (updated) {
					return new ResponseEntity<String>("Successfully updated!", HttpStatus.OK);
				} else {
					return new ResponseEntity<String>("Update failed!", HttpStatus.EXPECTATION_FAILED);
				}
			}

		}

	}

	@DeleteMapping("/delete/{id}")
	public Object deleteCategory(@PathVariable("id") int id) {
		Target entity = targetService.findById(id).get();
		if (entity != null) {
			targetService.removeById(id);
			return new ResponseEntity<String>("Successully deleted!", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Not found entity!", HttpStatus.NOT_FOUND);
		}
	}

}
