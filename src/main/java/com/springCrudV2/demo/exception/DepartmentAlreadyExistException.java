package com.springCrudV2.demo.exception;

public class DepartmentAlreadyExistException extends RuntimeException {
    public DepartmentAlreadyExistException() {
        super("Department with this name is already exist in DB");
    }
}