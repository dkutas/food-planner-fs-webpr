package com.fs.webpr.foodplanner_backend.exception;

@SuppressWarnings("unused")
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
