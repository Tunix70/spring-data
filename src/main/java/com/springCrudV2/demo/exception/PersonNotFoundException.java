package com.springCrudV2.demo.exception;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(Long id) {
        super("Couldn't find person with id:" + id);
    }
}