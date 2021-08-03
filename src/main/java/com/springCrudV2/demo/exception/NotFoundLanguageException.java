package com.springCrudV2.demo.exception;

public class NotFoundLanguageException extends Exception{
    private final String message;

    public NotFoundLanguageException(String message) {
        this.message = message;
        System.out.println("Not found" + message);
    }
}
