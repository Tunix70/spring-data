package com.springCrudV2.demo.validator;

import com.springCrudV2.demo.dto.LanguageDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("beforeCreateLanguageDtoValidator")
public class LanguageDtoValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return LanguageDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
                "blank", "Language name can't be blank");
        LanguageDto dto = (LanguageDto) target;
        if (dto.getName() != null && (dto.getName().length() < 2 || dto.getName().length() > 30)) {
            errors.reject("length.beyond.borders", "Language name length must be between 2 and 30 characters");
        }
        if (dto.getName() != null && !dto.getName().isBlank() && !Character.isUpperCase(dto.getName().charAt(0))) {
            errors.reject("capital.letters", "Language name must start with capital letters");
        }
        if (dto.getName() != null && !dto.getName().isBlank() && !dto.getName().substring(1).matches("^[a-z]+$")) {
            errors.reject("small.letters", "Language name must contain only small letters after first letter");
        }
    }
}