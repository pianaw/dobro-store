package ru.kpfu.ds.mainservice.model.exception;

import ru.kpfu.ds.mainservice.model.annotation.InternalException;
import ru.kpfu.ds.mainservice.model.enums.ExceptionType;

@InternalException(type = ExceptionType.TOKEN_NOT_VERIFIED)
public class DobrostoreTokenVerificationException extends DobrostoreInternalException {
    public DobrostoreTokenVerificationException(String message) {
        super(message);
    }
}
