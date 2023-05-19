package com.example.core.exception;

public class DuplicatePersonEmailException extends RuntimeException {

    public DuplicatePersonEmailException() {
    }

    public DuplicatePersonEmailException(String message) {
        super(message);
    }
}
