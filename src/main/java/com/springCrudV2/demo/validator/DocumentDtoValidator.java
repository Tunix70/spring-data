package com.springCrudV2.demo.validator;


import com.springCrudV2.demo.dto.DocumentDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;


@Component("beforeCreateDocumentDtoValidator")
public class DocumentDtoValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return DocumentDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        DocumentDto dto = (DocumentDto) target;
        if (dto.getExpiry_date() == null) {
            errors.reject("date.is.null", "Date can't be null");
        }
        if (dto.getExpiry_date().toLocalDate().getYear() < LocalDate.now().getYear()) {
            errors.reject("date.is.in.the.past", "Date can't be in the past");
        }
    }
}