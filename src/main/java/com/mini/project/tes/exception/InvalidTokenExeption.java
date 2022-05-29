package com.mini.project.tes.exception;

public class InvalidTokenExeption extends RuntimeException {
    public InvalidTokenExeption(String message) {
        super(message);
    }

    public InvalidTokenExeption(String message, Throwable cause) {
        super(message, cause);
    }
}
