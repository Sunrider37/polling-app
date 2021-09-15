package com.example.quizapp.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String s) {
        super(s);
    }
}
