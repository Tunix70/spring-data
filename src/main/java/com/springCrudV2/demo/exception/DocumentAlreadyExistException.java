package com.springCrudV2.demo.exception;

public class DocumentAlreadyExistException extends RuntimeException {
    public DocumentAlreadyExistException() {
        super("Document with this ID is already exist in DB");
    }
}