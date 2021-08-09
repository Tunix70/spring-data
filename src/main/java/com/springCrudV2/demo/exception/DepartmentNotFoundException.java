package com.springCrudV2.demo.exception;

public class DepartmentNotFoundException extends RuntimeException {
    public DepartmentNotFoundException(Long id) {
        super("Couldn't find department with id:" + id);
    }
}