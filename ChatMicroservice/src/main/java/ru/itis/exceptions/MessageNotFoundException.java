package ru.itis.exceptions;

import lombok.Getter;

@Getter
public class MessageNotFoundException extends RuntimeException{
    private final ExceptionEntity exceptionEntity;

    public MessageNotFoundException(ExceptionEntity exceptionEntity) {
        super(exceptionEntity.getMessage());
        this.exceptionEntity = exceptionEntity;
    }
}
