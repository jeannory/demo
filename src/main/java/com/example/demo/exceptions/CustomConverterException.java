package com.example.demo.exceptions;

public class CustomConverterException extends RuntimeException {

    public CustomConverterException() {
    }

    public CustomConverterException(String message) {
        super(message);
    }
}
