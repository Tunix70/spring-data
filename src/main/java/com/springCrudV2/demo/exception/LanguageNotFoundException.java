package com.springCrudV2.demo.exception;

public class LanguageNotFoundException extends RuntimeException {
    public LanguageNotFoundException(Long id) {
        super("Couldn't find language with id:" + id);
    }
}