package com.kenm.spring.farmleaseservice.dto.validation.Impl;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import com.kenm.spring.farmleaseservice.dto.validation.ValidIsoDate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IsoDateValidator implements ConstraintValidator<ValidIsoDate, LocalDate> {

	@Override
	public void initialize(ValidIsoDate constraintAnnotation) {
	}

	@Override
	public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
		if (value == null) {
			return true; // Null values should be handled by @NotNull
		}
		try {
			LocalDate.parse(value.toString()); // This will throw exception if format is invalid
			return true;
		} catch (DateTimeParseException e) {
			return false;
		}
	}
}
