package com.springCrudV2.demo.exception;

public class LanguageNameNotValidException extends RuntimeException {
    public LanguageNameNotValidException(String message) {
        super(message);
    }
}