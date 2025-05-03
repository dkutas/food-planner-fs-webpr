package com.fs.webpr.foodplanner_backend.exception;

@SuppressWarnings("unused")
public class AlreadyExistsException extends RuntimeException {

    public AlreadyExistsException() {
        super();
    }

    public AlreadyExistsException(String message) {
        super(message);
    }
}

