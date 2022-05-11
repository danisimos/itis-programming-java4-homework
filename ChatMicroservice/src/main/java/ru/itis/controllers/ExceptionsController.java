package ru.itis.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.itis.exceptions.*;

@ControllerAdvice
public class ExceptionsController {
    @ExceptionHandler
    public ResponseEntity<ExceptionEntity> handleValidationException(CustomValidationException customValidationException) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(customValidationException.getExceptionEntity());
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionEntity> handleAccountNotFoundException(AccountNotFoundException accountNotFoundException) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(accountNotFoundException.getExceptionEntity());
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionEntity> handleAccountNotFoundException(ChatRoomNotFoundException chatRoomNotFoundException) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(chatRoomNotFoundException.getExceptionEntity());
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionEntity> handleAccountNotFoundException(MessageNotFoundException messageNotFoundException) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(messageNotFoundException.getExceptionEntity());
    }
}
