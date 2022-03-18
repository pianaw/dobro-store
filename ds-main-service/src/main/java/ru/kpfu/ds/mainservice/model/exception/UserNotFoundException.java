package ru.kpfu.ds.mainservice.model.exception;

import ru.kpfu.ds.mainservice.model.annotation.InternalException;
import ru.kpfu.ds.mainservice.model.enums.ExceptionType;

@InternalException(type = ExceptionType.USER_NOT_FOUND)
public class UserNotFoundException extends DobrostoreInternalException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
