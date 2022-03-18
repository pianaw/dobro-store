package ru.kpfu.ds.mainservice.model.exception;

import ru.kpfu.ds.mainservice.model.annotation.InternalException;
import ru.kpfu.ds.mainservice.model.enums.ExceptionType;

@InternalException(type = ExceptionType.PASSWORD_MISMATCH)
public class PasswordMismatchException extends DobrostoreInternalException {

    public PasswordMismatchException(String message) {
        super(message);
    }
}
