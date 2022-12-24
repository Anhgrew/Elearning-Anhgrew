package com.myclass.api.controller;

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

import com.myclass.entity.Category;
import com.myclass.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class ApiCategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/index")
	public Object getAll() {
		List<Category> categories = categoryService.findAll();
		if (categories.isEmpty()) {
			return new ResponseEntity<String>("No content!", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public Object getOne(@PathVariable("id") int id) {
		Category entity = categoryService.findById(id).get();
		if (entity != null) {
			return new ResponseEntity<Category>(entity, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Not found entity!", HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("/add")
	public Object addCategory(@Valid @RequestBody Category category, BindingResult errors) {
		boolean created = categoryService.add(category);
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
	public Object updateCategory(@PathVariable("id") int id, @Valid @RequestBody Category category,
			BindingResult errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity<Object>(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		} else {
			Category entity = categoryService.findById(id).get();
			if (entity == null) {
				return new ResponseEntity<String>("Not found entity!", HttpStatus.NOT_FOUND);
			} else {
				boolean updated = categoryService.update(entity);
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
		Category entity = categoryService.findById(id).get();
		if (entity != null) {
			categoryService.removeById(id);
			return new ResponseEntity<String>("Successully deleted!", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Not found entity!", HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "/uploadIcon", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Object uploadIcon(@RequestParam("id") int id, @RequestPart("file") MultipartFile fileUploaded) {
		return categoryService.setImage(fileUploaded, id);
	}

	@GetMapping(value = "/getIcon", produces = "image/*")
	public Object getIcon(@RequestParam("id") int id) {
		return categoryService.getImage(id);
	}
}