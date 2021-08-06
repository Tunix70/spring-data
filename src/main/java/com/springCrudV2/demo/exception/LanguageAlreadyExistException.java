package com.springCrudV2.demo.exception;

public class LanguageAlreadyExistException extends RuntimeException {
    public LanguageAlreadyExistException() {
        super("Language with this name is already exist in DB");
    }
}