package com.myclass.admin.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myclass.entity.Role;
import com.myclass.service.RoleService;

@Controller
@RequestMapping("admin/role")
public class AdminRoleController {
	@Autowired
	private RoleService roleService;

	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("action", "/admin/role");
		model.addAttribute("roles", roleService.findAll());
		return "role/index";
	}

	@GetMapping("add")
	public String add(Model model) {
		model.addAttribute("action", "/admin/role");
		model.addAttribute("role", new Role());
		return "role/add";
	}

	@PostMapping("search")
	public String search(@RequestParam("keyword") String keyword, ModelMap model) {
		model.addAttribute("roles", roleService.findAllByKeyWord(keyword));
		model.addAttribute("action", "/admin/role");
		return "role/index";
	}

	@PostMapping("add")
	public String add(Model model, @Valid @ModelAttribute("role") Role role, BindingResult errors) {
		if (errors.hasErrors()) {
			return "role/add";
		}
		model.addAttribute("role", new Role());
		roleService.add(role);
		return "redirect:/admin/role";
	}

	@GetMapping("edit/{id}")
	public String edit(Model model, @PathVariable("id") String id) {
		model.addAttribute("action", "/admin/role");
		Optional<Role> role = roleService.findById(id);
		if (role != null) {
			model.addAttribute("roleEdit", role);
			return "role/edit";
		} else {
			return "redirect:/admin/role";
		}
	}

	@PostMapping("edit")
	public String edit(Model model, @Valid @ModelAttribute("roleEdit") Role role, BindingResult errors) {
		if (errors.hasErrors()) {
			model.addAttribute("roleEdit", role);
			return "role/edit";
		}
		roleService.update(role);
		return "redirect:/admin/role";

	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") String id) {
		roleService.removeById(id);
		return "redirect:/admin/role";
	}
}
