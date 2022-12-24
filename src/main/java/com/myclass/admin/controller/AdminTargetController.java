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
import com.myclass.entity.Target;

import com.myclass.service.CourseService;
import com.myclass.service.TargetService;

@Controller
@RequestMapping("admin/target")
public class AdminTargetController {

	@Autowired
	private TargetService targetService;

	@Autowired
	private CourseService courseService;

	@GetMapping("")
	public String index(ModelMap model) {
		model.addAttribute("action", "/admin/target");
		model.addAttribute("targets", targetService.findAll());
		return "target/index";
	}

	@GetMapping("{id}")
	public String index(ModelMap model, @PathVariable("id") int id) {
		model.addAttribute("action", "/admin/course");
		model.addAttribute("targets", targetService.findByCourseId(id));
		return "target/indexToCourse";
	}

	@GetMapping("add/{id}")
	public String addToCourse(ModelMap model, @PathVariable("id") int id) {
		model.addAttribute("action", "/admin/course");
		model.addAttribute("target", new Target());
		Course courses = courseService.findById(id).get();
		model.addAttribute("courses", courses);
		return "target/add";
	}

	@PostMapping("search")
	public String search(@RequestParam("keyword") String keyword, ModelMap model) {
		model.addAttribute("action", "/admin/target");
		model.addAttribute("targets", targetService.findAllByKeyWord(keyword));
		return "target/index";
	}

	@GetMapping("add")
	public String add(ModelMap model) {
		model.addAttribute("action", "/admin/target");
		model.addAttribute("target", new Target());
		model.addAttribute("courses", courseService.findAll());
		return "target/add";
	}

	@PostMapping("add")
	public String add(ModelMap model, @Valid @ModelAttribute("target") Target target, BindingResult errors) {

		if (errors.hasErrors()) {
			model.addAttribute("courses", courseService.findAll());
			return "target/add";
		}
		targetService.add(target);
		return "redirect:/admin/target";
	}

	@GetMapping("edit/{id}")
	public String edit(@PathVariable("id") int id, ModelMap model) {
		model.addAttribute("action", "/admin/target");
		Optional<Target> target = targetService.findById(id);
		model.addAttribute("target", target);
		model.addAttribute("courses", courseService.findAll());
		return "target/edit";
	}

	@PostMapping("edit")
	public String edit(ModelMap model, @Valid @ModelAttribute("target") Target target, BindingResult errors) {

		if (errors.hasErrors()) {
			model.addAttribute("courses", courseService.findAll());
			return "target/edit";
		}
		targetService.update(target);
		return "redirect:/admin/target";
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") int id) {
		targetService.removeById(id);
		return "redirect:/admin/target";
	}
}
