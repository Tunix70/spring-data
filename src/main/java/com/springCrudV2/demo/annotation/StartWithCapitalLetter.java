package com.springCrudV2.demo.annotation;

import com.springCrudV2.demo.validator.CapitalLetterValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = CapitalLetterValidator.class)
@Documented
public @interface StartWithCapitalLetter {
    String message() default "Must start with capital letter";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}