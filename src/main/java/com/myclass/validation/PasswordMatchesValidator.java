package com.myclass.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.myclass.dto.RegisterDto;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

	@Override
	public void initialize(PasswordMatches constraintAnnotation) {
	}

	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context) {

		RegisterDto user = (RegisterDto) obj;
		if (user.getPassword().equals(user.getConfirm())) {
			return true;
		} else {
			return false;
		}

	}
}
