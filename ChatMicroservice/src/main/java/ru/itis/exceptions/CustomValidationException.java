package ru.itis.exceptions;

import lombok.Getter;

@Getter
public class CustomValidationException extends RuntimeException{
    private final ExceptionEntity exceptionEntity;

    public CustomValidationException(ExceptionEntity exceptionEntity) {
        super(exceptionEntity.getMessage());
        this.exceptionEntity = exceptionEntity;
    }
}
