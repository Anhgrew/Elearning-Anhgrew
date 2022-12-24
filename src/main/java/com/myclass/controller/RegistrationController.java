package com.myclass.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import com.myclass.dto.RegisterDto;
import com.myclass.service.RegisterService;
import com.myclass.validation.EmailExistsException;

@Controller
public class RegistrationController {

	@Autowired
	private RegisterService registerService;

	@GetMapping("registration")
	public String showRegistrationForm(WebRequest request, ModelMap model) {
		model.addAttribute("register", new RegisterDto());
		return "registration/index";
	}

	@PostMapping("registration")
	public String registerUserAccount(@ModelAttribute("register") @Valid RegisterDto accountDto, BindingResult result,
			WebRequest request, Errors errors, ModelMap model) throws EmailExistsException {
		boolean emailExist = registerService.emailExist(accountDto.getEmail());
		boolean confirmMatching = accountDto.getPassword().equals(accountDto.getConfirm());
		if (!result.hasErrors() && !emailExist && confirmMatching) {
			registerService.registerNewUserAccount(accountDto);
			return "redirect:/course";
		} else {
			if (emailExist) {
				model.addAttribute("message", "This address is already used with another account!");
			}
			if (confirmMatching) {
				model.addAttribute("confirmMessage", "Password confirm and password don't match!");
			}
			return "registration/index";
		}
	}

}
