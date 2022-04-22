package ru.kpfu.ds.mainservice.model.exception;

import ru.kpfu.ds.mainservice.model.annotation.InternalException;
import ru.kpfu.ds.mainservice.model.enums.ExceptionType;

@InternalException(type = ExceptionType.CLIENT_NOT_FOUND)
public class ClientNotFoundException extends DobrostoreInternalException {

    public ClientNotFoundException(String message) {
        super(message);
    }
}
