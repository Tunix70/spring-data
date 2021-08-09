package com.springCrudV2.demo.validator;

import com.springCrudV2.demo.annotation.StartWithCapitalLetter;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component("beforeCreateCapitalLetterValidator")
public class CapitalLetterValidator implements ConstraintValidator<StartWithCapitalLetter, String> {
    @Override
    public void initialize(StartWithCapitalLetter constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && !value.isBlank() && Character.isUpperCase(value.charAt(0));
    }
}