package com.springCrudV2.demo.exception;

public class DocumentAlreadyTakenException extends RuntimeException {
    public DocumentAlreadyTakenException() {
        super("Document has already taken by other person");
    }
}