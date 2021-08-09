package com.springCrudV2.demo.validator;

import com.springCrudV2.demo.annotation.SmallLettersSubstring;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component("beforeCreateSmallLetterValidator")
public class SmallLetterValidator implements ConstraintValidator<SmallLettersSubstring, String> {
    @Override
    public void initialize(SmallLettersSubstring constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && !value.isBlank() && value.substring(1).matches("^[a-z]+$");
    }
}