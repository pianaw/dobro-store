package ru.kpfu.ds.mainservice.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExceptionType {

    UNKNOWN(1, HttpStatus.INTERNAL_SERVER_ERROR, "Unknown server error"),
    USER_NOT_FOUND(2, HttpStatus.NOT_FOUND, "User not found"),
    PASSWORD_MISMATCH(3, HttpStatus.CONFLICT, "Invalid password"),
    CLIENT_NOT_FOUND(4, HttpStatus.NOT_FOUND, "Client not found"),
    TOKEN_EXPIRED(5, HttpStatus.UNAUTHORIZED, "Token is expired"),
    TOKEN_NOT_VERIFIED(6, HttpStatus.UNAUTHORIZED, "Token is not verified"),
    ACCESS_DENIED(7, HttpStatus.UNAUTHORIZED, "Access is denied"),
    USER_ALREADY_EXISTS(8, HttpStatus.CONFLICT, "User already exists");

    private Integer code;
    private HttpStatus httpStatus;
    private String defaultMessage;
}
