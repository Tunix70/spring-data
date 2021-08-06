package com.springCrudV2.demo.exception;

public class DocumentNotFoundException extends RuntimeException {
    public DocumentNotFoundException(String id) {
        super("Couldn't find document with id:" + id);
    }
}