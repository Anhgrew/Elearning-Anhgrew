package com.myclass.controller;

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

import com.myclass.entity.Category;
import com.myclass.service.CategoryService;

@Controller
@RequestMapping("category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;


	@GetMapping("")
	public String index(ModelMap model) {
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("action", "/admin/category");
		return "category/index";
	}

	@PostMapping("search")
	public String search(@RequestParam("keyword") String keyword, ModelMap model) {
		model.addAttribute("categories", categoryService.findAllByKeyWord(keyword));
		model.addAttribute("action", "/admin/category");
		return "category/index";
	}

	@GetMapping("add")
	public String add(ModelMap model) {
		model.addAttribute("category", new Category());
		model.addAttribute("action", "/admin/category");
		return "category/add";
	}

	@PostMapping("add")
	public String add(ModelMap model, @Valid @ModelAttribute("category") Category category, BindingResult errors) {

		if (errors.hasErrors()) {
			return "category/add";
		}
		categoryService.add(category);
		return "redirect:/admin/category";
	}

	@GetMapping("edit/{id}")
	public String edit(@PathVariable("id") int id, ModelMap model) {
		Optional<Category> category = categoryService.findById(id);
		model.addAttribute("action", "/admin/category");
		model.addAttribute("category", category);
		return "category/edit";
	}

	@PostMapping("edit")
	public String edit(ModelMap model, @Valid @ModelAttribute("category") Category category, BindingResult errors) {

		if (errors.hasErrors()) {
			return "category/edit";
		}
		categoryService.update(category);
		return "redirect:/admin/category";
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") int id) {
		categoryService.removeById(id);
		return "redirect:/admin/category";
	}
}
