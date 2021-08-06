package com.springCrudV2.demo.advice;

import com.springCrudV2.demo.exception.StartWithCapitalLetterException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class StartWithCapitalLetterAdvice {
    @ResponseBody
    @ExceptionHandler(StartWithCapitalLetterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String startWithCapitalLetter(StartWithCapitalLetterException e) {
        return e.getMessage();
    }
}