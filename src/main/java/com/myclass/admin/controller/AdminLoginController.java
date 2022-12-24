package com.myclass.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("admin")
public class AdminLoginController {

	@GetMapping("login")
	public String index(@RequestParam(required = false) String error, Model model) {
		String message = "";
		if (error != null && !error.isEmpty()) {
			message = "Login failed. Please try again !!!";
		}
		model.addAttribute("message", message);
		return "login/index";
	}

}
