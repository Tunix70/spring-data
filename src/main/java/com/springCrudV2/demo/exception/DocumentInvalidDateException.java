package com.springCrudV2.demo.exception;

public class DocumentInvalidDateException extends RuntimeException {
    public DocumentInvalidDateException(String message) {
        super(message);
    }
}