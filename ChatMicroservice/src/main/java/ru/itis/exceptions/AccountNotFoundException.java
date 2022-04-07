package ru.itis.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
public class AccountNotFoundException extends RuntimeException{
    private final ExceptionEntity exceptionEntity;

    public AccountNotFoundException(ExceptionEntity exceptionEntity) {
        super(exceptionEntity.getMessage());
        this.exceptionEntity = exceptionEntity;
    }
}
