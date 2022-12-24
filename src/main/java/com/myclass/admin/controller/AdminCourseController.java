package com.myclass.admin.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myclass.entity.Course;
import com.myclass.service.CategoryService;
import com.myclass.service.CourseService;

@Controller
@RequestMapping("admin/course")
public class AdminCourseController {

	@Autowired
	private CourseService courseService;

	@Autowired
	private CategoryService categoryService;

	@GetMapping("")
	public String index(ModelMap model) {
		model.addAttribute("action", "/admin/course");
		model.addAttribute("courses", courseService.findAll());
		return "course/index";
	}

	@PostMapping("search")
	public String search(@RequestParam("keyword") String keyword, ModelMap model) {
		model.addAttribute("action", "/admin/course");
		model.addAttribute("courses", courseService.findAllByKeyWord(keyword));
		return "course/index";
	}

	@GetMapping("add")
	public String add(ModelMap model) {
		model.addAttribute("action", "/admin/course");
		model.addAttribute("course", new Course());
		model.addAttribute("categories", categoryService.findAll());
		return "course/add";
	}

	@PostMapping("add")
	public String add(ModelMap model, @Valid @ModelAttribute("course") Course course, BindingResult errors) {

		if (errors.hasErrors()) {
			model.addAttribute("categories", categoryService.findAll());
			return "course/add";
		}
		course.setLecturerId("7087a795-add3-4110-bd35-fa4f23e9d89d");
		courseService.add(course);
		return "redirect:/admin/course";
	}

	@GetMapping("edit/{id}")
	public String edit(@PathVariable("id") int id, ModelMap model) {
		model.addAttribute("action", "/admin/course");
		Optional<Course> course = courseService.findById(id);
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("course", course);
		return "course/edit";
	}

	@PostMapping("edit")
	public String edit(ModelMap model, @Valid @ModelAttribute("course") Course course, BindingResult errors) {

		if (errors.hasErrors()) {
			model.addAttribute("categories", categoryService.findAll());
			return "course/edit";
		}
		courseService.update(course);
		return "redirect:/admin/course";
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") int id) {
		courseService.removeById(id);
		return "redirect:/admin/course";
	}
}
