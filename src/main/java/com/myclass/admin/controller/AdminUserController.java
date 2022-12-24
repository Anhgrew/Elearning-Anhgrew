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

import com.myclass.entity.User;
import com.myclass.service.RoleService;
import com.myclass.service.UserService;

@Controller
@RequestMapping("admin/user")
public class AdminUserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@GetMapping("")
	public String index(ModelMap model) {
		model.addAttribute("action", "/admin/user");
		model.addAttribute("users", userService.findAll());
		return "user/index";
	}

	@PostMapping("search")
	public String search(@RequestParam("keyword") String keyword, ModelMap model) {
		model.addAttribute("action", "/admin/user");
		model.addAttribute("users", userService.findAllByKeyWord(keyword));
		return "user/index";
	}

	@GetMapping("add")
	public String add(ModelMap model) {
		model.addAttribute("action", "/admin/user");
		model.addAttribute("roles", roleService.findAll());
		model.addAttribute("user", new User());
		return "user/add";
	}

	@PostMapping("add")
	public String add(ModelMap model, @Valid @ModelAttribute("user") User user, BindingResult errors) {
		if (errors.hasErrors()) {
			model.addAttribute("roles", roleService.findAll());
			return "user/add";
		}
		userService.add(user);
		return "redirect:/admin/user";
	}

	@GetMapping("edit/{id}")
	public String edit(@PathVariable("id") String id, ModelMap model) {
		model.addAttribute("action", "/admin/user");
		Optional<User> user = userService.findById(id);
		model.addAttribute("roles", roleService.findAll());
		model.addAttribute("user", user);
		return "user/edit";
	}

	@PostMapping("edit")
	public String edit(ModelMap model, @Valid @ModelAttribute("user") User user, BindingResult errors) {
		if (errors.hasErrors()) {
			model.addAttribute("roles", roleService.findAll());
			return "user/edit";
		}
		userService.update(user);
		return "redirect:/admin/user";
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") String id) {
		userService.removeById(id);
		return "redirect:/admin/user";
	}
}
