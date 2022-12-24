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

import com.myclass.entity.Video;
import com.myclass.service.VideoService;

@RestController
@RequestMapping("/api/video")
public class ApiVideoController {

	@Autowired
	private VideoService videoService;

	@GetMapping("/index")
	public Object getAll() {
		List<Video> videos = videoService.findAll();
		if (videos.isEmpty()) {
			return new ResponseEntity<String>("No content!", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Video>>(videos, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public Object getOne(@PathVariable("id") int id) {
		Video entity = videoService.findById(id).get();
		if (entity != null) {
			return new ResponseEntity<Video>(entity, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Not found entity!", HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("/add")
	public Object addRole(@Valid @RequestBody Video video, BindingResult errors) {
		boolean created = videoService.add(video);
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
	public Object updateCategory(@PathVariable("id") int id, @Valid @RequestBody Video video, BindingResult errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity<Object>(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		} else {
			Video entity = videoService.findById(id).get();
			if (entity == null) {
				return new ResponseEntity<String>("Not found entity!", HttpStatus.NOT_FOUND);
			} else {
				boolean updated = videoService.update(entity);
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
		Video entity = videoService.findById(id).get();
		if (entity != null) {
			videoService.removeById(id);
			return new ResponseEntity<String>("Successully deleted!", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Not found entity!", HttpStatus.NOT_FOUND);
		}
	}

}
