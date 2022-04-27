package ru.kpfu.ds.mainservice.model.exception;

import ru.kpfu.ds.mainservice.model.annotation.InternalException;
import ru.kpfu.ds.mainservice.model.enums.ExceptionType;

@InternalException(type = ExceptionType.USER_ALREADY_EXISTS)
public class UserAlreadyExistsException extends DobrostoreInternalException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
