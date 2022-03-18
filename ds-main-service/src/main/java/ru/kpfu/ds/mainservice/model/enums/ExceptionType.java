package ru.kpfu.ds.mainservice.model.enums;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum ExceptionType {

    UNKNOWN(HttpStatus.INTERNAL_SERVER_ERROR, "Unknown server error"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found"),
    PASSWORD_MISMATCH(HttpStatus.CONFLICT, "Invalid password");

    private HttpStatus httpStatus;
    private String defaultMessage;
}
