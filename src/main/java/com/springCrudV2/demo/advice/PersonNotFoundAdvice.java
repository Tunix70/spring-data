package com.springCrudV2.demo.advice;

import com.springCrudV2.demo.exception.PersonNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PersonNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(PersonNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String personNotFoundAdvice(PersonNotFoundException e) {
        return e.getMessage();
    }
}