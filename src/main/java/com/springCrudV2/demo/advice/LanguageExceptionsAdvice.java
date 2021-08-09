package com.springCrudV2.demo.advice;

import com.springCrudV2.demo.exception.LanguageAlreadyExistException;
import com.springCrudV2.demo.exception.LanguageNameNotValidException;
import com.springCrudV2.demo.exception.LanguageNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class LanguageExceptionsAdvice {
    @ResponseBody
    @ExceptionHandler(LanguageNameNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String languageNameIsBlankAdvice(LanguageNameNotValidException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(LanguageNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String languageNotFoundAdvice(LanguageNotFoundException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(LanguageAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String languageAlreadyExistException(LanguageAlreadyExistException e) {
        return e.getMessage();
    }
}