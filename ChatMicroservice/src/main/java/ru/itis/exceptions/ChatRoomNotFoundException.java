package ru.itis.exceptions;

import lombok.Getter;

@Getter
public class ChatRoomNotFoundException extends RuntimeException{
    private final ExceptionEntity exceptionEntity;

    public ChatRoomNotFoundException(ExceptionEntity exceptionEntity) {
        super(exceptionEntity.getMessage());
        this.exceptionEntity = exceptionEntity;
    }
}
