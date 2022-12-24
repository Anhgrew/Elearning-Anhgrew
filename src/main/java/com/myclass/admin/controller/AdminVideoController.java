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
import com.myclass.entity.Video;
import com.myclass.service.CourseService;
import com.myclass.service.VideoService;

@Controller
@RequestMapping("admin/video")
public class AdminVideoController {

	@Autowired
	private VideoService videoService;

	@Autowired
	private CourseService courseService;

	@GetMapping("")
	public String index(ModelMap model) {
		model.addAttribute("action", "/admin/video");
		model.addAttribute("videos", videoService.findAll());
		return "video/index";
	}

	@GetMapping("{id}")
	public String index(ModelMap model, @PathVariable("id") int id) {
		model.addAttribute("action", "/admin/course");
		model.addAttribute("videos", videoService.findByCourseId(id));
		return "video/indexToCourse";
	}

	// Phương thức tìm kiếm
	@PostMapping("search")
	public String search(@RequestParam("keyword") String keyword, ModelMap model) {
		model.addAttribute("action", "/admin/video");
		model.addAttribute("videos", videoService.findAllByKeyWord(keyword));
		return "video/index";
	}

	@GetMapping("add")
	public String add(ModelMap model) {
		model.addAttribute("action", "/admin/video");
		model.addAttribute("video", new Video());
		model.addAttribute("courses", courseService.findAll());
		return "video/add";
	}

	@GetMapping("addToCourse/{id}")
	public String addToCourse(ModelMap model, @PathVariable("id") int id) {
		model.addAttribute("action", "/admin/course");
		model.addAttribute("video", new Video());
		Course courses = courseService.findById(id).get();
		model.addAttribute("courses", courses);
		return "video/addToCourse";
	}

	@PostMapping("add")
	public String add(ModelMap model, @Valid @ModelAttribute("video") Video video, BindingResult errors) {
		// Nếu có lỗi
		if (errors.hasErrors()) {
			model.addAttribute("courses", courseService.findAll());
			return "video/add";
		}
		videoService.add(video);
		return "redirect:/admin/video";
	}

	@PostMapping("addToCourse")
	public String addToCourse(ModelMap model, @Valid @ModelAttribute("video") Video video, BindingResult errors) {
		model.addAttribute("action", "/admin/course");
		if (errors.hasErrors()) {
			model.addAttribute("courses", courseService.findAll());
			return "course/index";
		}
		videoService.add(video);
		return "redirect:/admin/course";
	}

	// Lấy thông tin videos theo id
	@GetMapping("edit/{id}")
	public String edit(@PathVariable("id") int id, ModelMap model) {
		model.addAttribute("action", "/admin/video");
		Optional<Video> video = videoService.findById(id);
		model.addAttribute("video", video);
		model.addAttribute("courses", courseService.findAll());
		return "video/edit";
	}

	@PostMapping("edit")
	public String edit(ModelMap model, @Valid @ModelAttribute("video") Video video, BindingResult errors) {
		// Nếu có lỗi
		if (errors.hasErrors()) {
			return "video/edit";
		}
		videoService.update(video);
		return "redirect:/admin/video";
	}

	// Lấy thông tin videos theo id
	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") int id) {
		videoService.removeById(id);
		return "redirect:/admin/video";
	}
}
