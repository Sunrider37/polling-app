package com.example.quizapp.exception;

import javax.validation.constraints.NotNull;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String choice, String id, @NotNull Long choiceId) {
    }
}
