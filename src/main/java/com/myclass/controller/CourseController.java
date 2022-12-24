package com.myclass.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myclass.dto.LoginDto;
import com.myclass.dto.RegisterDto;
import com.myclass.entity.Category;
import com.myclass.entity.User;
import com.myclass.entity.UserCourse;
import com.myclass.service.AuthenticationService;
import com.myclass.service.CategoryService;
import com.myclass.service.CourseService;
import com.myclass.service.UserCourseService;
import com.myclass.service.UserService;
import com.myclass.service.VideoService;

@Controller
@RequestMapping("course")
public class CourseController {

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private UserService userService;

	@Autowired
	private VideoService videoService;

	@Autowired
	private UserCourseService userCourseService;

	@GetMapping("")
	public String index(ModelMap model) {
		User user = null;
		LoginDto currentAccount = authenticationService.getCurrentLoginAccount();
		if (currentAccount != null) {
			user = userService.findById(currentAccount.getId()).get();
			model.addAttribute("enrolls", userCourseService.countByUserId(currentAccount.getId()));

		}
		model.addAttribute("register", new RegisterDto());
		model.addAttribute("user", user);
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("courses", courseService.findAll());
		model.addAttribute("message", "Welcome to Elearning!");
		return "client/course/index";
	}

	@GetMapping("detail/{id}")
	public String detail(ModelMap model, @PathVariable("id") int id) {
		User user = null;
		LoginDto currentAccount = authenticationService.getCurrentLoginAccount();
		if (currentAccount != null) {
			user = userService.findById(currentAccount.getId()).get();
			model.addAttribute("enrolls", userCourseService.countByUserId(currentAccount.getId()));
		}
		model.addAttribute("user", user);
		model.addAttribute("userCourse", new UserCourse());
		model.addAttribute("videos", videoService.urlId(id));
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("course", courseService.findById(id).get());
		model.addAttribute("courses", courseService.findByCategoryId(id));
		return "client/course/detail";
	}

	@GetMapping("search")
	public String courseOfCategory(ModelMap model, @RequestParam(value = "categoryId", required = false) int id) {
		User user = null;
		LoginDto currentAccount = authenticationService.getCurrentLoginAccount();
		if (currentAccount != null) {
			user = userService.findById(currentAccount.getId()).get();
			model.addAttribute("enrolls", userCourseService.countByUserId(currentAccount.getId()));
		}
		model.addAttribute("user", user);
		Optional<Category> category = categoryService.findById(id);
		model.addAttribute("category", category.get());
		model.addAttribute("categories", categoryService.findAll());

		model.addAttribute("courses", courseService.findByCategoryId(id));
		return "client/course/index";
	}

	@PostMapping("search")
	public String search(@RequestParam(required = false, value = "keyword") String keyword, ModelMap model) {
		User user = null;
		LoginDto currentAccount = authenticationService.getCurrentLoginAccount();
		if (currentAccount != null) {
			user = userService.findById(currentAccount.getId()).get();
			model.addAttribute("enrolls", userCourseService.countByUserId(currentAccount.getId()));
		}
		model.addAttribute("user", user);
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("courses", courseService.findAllByKeyWord(keyword));
		return "client/course/index";
	}

}
