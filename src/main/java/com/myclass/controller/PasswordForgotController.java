package com.myclass.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myclass.dto.PasswordForgotDto;
import com.myclass.entity.Mail;
import com.myclass.entity.PasswordResetToken;
import com.myclass.entity.User;
import com.myclass.repository.PasswordResetTokenRepository;
import com.myclass.service.EmailService;
import com.myclass.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("forgot-password")
public class PasswordForgotController {

	@Autowired
	private UserService userService;
	@Autowired
	private PasswordResetTokenRepository tokenRepository;
	@Autowired
	private EmailService emailService;

	@ModelAttribute("forgotPasswordForm")
	public PasswordForgotDto forgotPasswordDto() {
		return new PasswordForgotDto();
	}

	@GetMapping
	public String displayForgotPasswordPage() {
		return "forgotpassword/forgot-password";
	}

	@PostMapping
	public String processForgotPasswordForm(@ModelAttribute("forgotPasswordForm") @Valid PasswordForgotDto form,
			BindingResult result, HttpServletRequest request) {

		if (result.hasErrors()) {
			return "forgotpassword/forgot-password";
		}

		User user = userService.findByEmailAndDelete(form.getEmail());
		if (user == null) {
			result.rejectValue("email", null, "We could not find an account for that e-mail address.");
			return "forgotpassword/forgot-password";
		}

		PasswordResetToken token = new PasswordResetToken();
		token.setToken(UUID.randomUUID().toString());
		token.setUser(user);
		token.setExpiryDate(30);
		tokenRepository.save(token);

		Mail mail = new Mail();
		mail.setFrom("admin@gmail.com");
		mail.setTo(user.getEmail());
		mail.setSubject("Password reset request");

		Map<String, Object> model = new HashMap<>();
		model.put("token", token);
		model.put("user", user);
		model.put("signature", "E-LearningCenter.com");
		String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
		model.put("resetUrl", url + "/reset-password?token=" + token.getToken());
		mail.setModel(model);
		emailService.sendEmail(mail);

		return "redirect:/forgot-password?success";

	}

}