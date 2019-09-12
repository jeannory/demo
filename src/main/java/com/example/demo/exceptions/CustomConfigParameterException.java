package com.example.demo.exceptions;

public class CustomConfigParameterException extends RuntimeException {

    public CustomConfigParameterException() {
    }

    public CustomConfigParameterException(String message) {
        super(message);
    }
}
