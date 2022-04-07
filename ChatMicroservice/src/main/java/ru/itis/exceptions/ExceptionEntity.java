package ru.itis.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ExceptionEntity {
    ACCOUNT_ID_NULL(1, "Account id cant be null"),
    ACCOUNT_LOGIN_EMPTY(2, "Account login cant be empty"),
    ACCOUNT_WRONG_EMAIL(3, "Wrong email string"),
    ACCOUNT_FIRSTNAME_EMPTY(4, "Account first name cant be empty"),
    ACCOUNT_LASTNAME_EMPTY(5, "Account last name cant be empty"),
    ACCOUNT_WRONG_AGE(6, "Wrong account age"),

    CHAT_ROOM_ID_NULL(7, "Chat room id cant be null"),
    CHAT_ROOM_NAME_EMPTY(8, "Chat room name cant be empty"),

    MESSAGE_ID_NULL(9, "Message id cant be null"),
    MESSAGE_BODY_EMPTY(10, "Message body cant be empty"),

    ACCOUNT_NOT_DOUND(11, "No such account found"),
    CHAT_ROOM_NOT_FOUND(12, "No such chat room found"),
    MESSAGE_NOT_FOUND(13, "No such message found")
    ;

    int customStatus;
    String message;

    ExceptionEntity(int customStatus, String message) {
        this.customStatus = customStatus;
        this.message = message;
    }
}
