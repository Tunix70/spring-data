package com.springCrudV2.demo.annotation;

import com.springCrudV2.demo.validator.SmallLetterValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = SmallLetterValidator.class)
@Documented
public @interface SmallLettersSubstring {
    String message() default "Substring(1) must continue only with small letters";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}