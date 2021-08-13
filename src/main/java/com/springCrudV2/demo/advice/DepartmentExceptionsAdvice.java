package com.springCrudV2.demo.advice;

import com.springCrudV2.demo.exception.DepartmentAlreadyExistException;
import com.springCrudV2.demo.exception.DepartmentNotFoundException;
import com.springCrudV2.demo.validator.ValidationErrorResponse;
import com.springCrudV2.demo.validator.Violation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class DepartmentExceptionsAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onMethodArgumentNotValidException(
            MethodArgumentNotValidException e
    ) {
        final List<Violation> violations = e.getBindingResult().getFieldErrors().stream()
                .map(error -> new Violation(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ValidationErrorResponse(violations);
    }

    @ResponseBody
    @ExceptionHandler(DepartmentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String departmentNotFoundAdvice(DepartmentNotFoundException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(DepartmentAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String languageAlreadyExistException(DepartmentAlreadyExistException e) {
        return e.getMessage();
    }
}