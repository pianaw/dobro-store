package ru.kpfu.ds.mainservice.model.exception;

import ru.kpfu.ds.mainservice.model.annotation.InternalException;

@InternalException
public class UnknownException extends DobrostoreInternalException {

    public UnknownException(String message) {
        super(message);
    }
}
