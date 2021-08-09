package com.springCrudV2.demo.advice;

import com.springCrudV2.demo.exception.DocumentAlreadyExistException;
import com.springCrudV2.demo.exception.DocumentAlreadyTakenException;
import com.springCrudV2.demo.exception.DocumentInvalidDateException;
import com.springCrudV2.demo.exception.DocumentNotFoundException;
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
public class DocumentExceptionsAdvice {
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
    @ExceptionHandler(DocumentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String documentNotFoundAdvice(DocumentNotFoundException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(DocumentInvalidDateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String documentInvalidDateException(DocumentInvalidDateException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(DocumentAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String documentAlreadyExistException(DocumentAlreadyExistException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(DocumentAlreadyTakenException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String documentAlreadyTakenException(DocumentAlreadyTakenException e) {
        return e.getMessage();
    }
}