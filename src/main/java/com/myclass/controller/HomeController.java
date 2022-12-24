package com.myclass.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping(value = { " ", "/", "/home" })
	public String index() {
		return "redirect:/course";
	}
}
