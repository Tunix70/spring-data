package com.springCrudV2.demo.exception;

public class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String name) {
            super("Couldn't find user with name:" + name);
        }
}
