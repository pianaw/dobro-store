package ru.kpfu.ds.mainservice.model.exception;

import ru.kpfu.ds.mainservice.model.annotation.InternalException;
import ru.kpfu.ds.mainservice.model.enums.ExceptionType;

@InternalException(type = ExceptionType.TOKEN_EXPIRED)
public class DobrostoreTokenExpiredException extends DobrostoreInternalException {

    public DobrostoreTokenExpiredException(String message) {
        super(message);
    }
}
