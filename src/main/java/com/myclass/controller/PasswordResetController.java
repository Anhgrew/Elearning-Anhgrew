package com.myclass.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myclass.dto.PasswordResetDto;
import com.myclass.entity.PasswordResetToken;
import com.myclass.entity.User;
import com.myclass.repository.PasswordResetTokenRepository;
import com.myclass.service.UserService;

@Controller
@RequestMapping("/reset-password")
public class PasswordResetController {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordResetTokenRepository tokenRepository;

	@ModelAttribute("passwordResetForm")
	public PasswordResetDto passwordReset() {
		return new PasswordResetDto();
	}

	@GetMapping
	public String displayResetPasswordPage(@RequestParam(required = false) String token, Model model) {

		PasswordResetToken resetToken = tokenRepository.findByToken(token);
		if (resetToken == null) {
			model.addAttribute("error", "Could not find password reset token.");
		} else if (resetToken.isExpired()) {
			model.addAttribute("error", "Token has expired, please request a new password reset.");
		} else {
			model.addAttribute("token", resetToken.getToken());
		}
		return "forgotpassword/reset-password";
	}

	@PostMapping
	@Transactional
	public String handlePasswordReset(@ModelAttribute("passwordResetForm") @Valid PasswordResetDto form,
			BindingResult result, RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute(BindingResult.class.getName() + ".passwordResetForm", result);
			redirectAttributes.addFlashAttribute("passwordResetForm", form);
			return "redirect:/reset-password?token=" + form.getToken();
		}

		PasswordResetToken token = tokenRepository.findByToken(form.getToken());
		User user = token.getUser();
		String updatedPassword = BCrypt.hashpw(form.getPassword(), BCrypt.gensalt(12));
		userService.updatePassword(updatedPassword, user.getId());
		tokenRepository.delete(token);

		return "redirect:/login?resetSuccess";
	}

}