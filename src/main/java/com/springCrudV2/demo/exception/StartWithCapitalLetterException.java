package com.springCrudV2.demo.exception;

public class StartWithCapitalLetterException extends RuntimeException {
    public StartWithCapitalLetterException() {
        super("Must start with capital letters");
    }
}