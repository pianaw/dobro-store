package ru.kpfu.ds.mainservice.model.exception;

import ru.kpfu.ds.mainservice.model.annotation.InternalException;

@InternalException
public class DobrostoreInternalException extends RuntimeException {

    public DobrostoreInternalException(String message) {
        super(message);
    }
}
