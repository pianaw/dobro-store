package ru.kpfu.ds.mainservice.controller.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.kpfu.ds.mainservice.model.annotation.InternalException;
import ru.kpfu.ds.mainservice.model.dto.ErrorResponse;
import ru.kpfu.ds.mainservice.model.enums.ExceptionType;
import ru.kpfu.ds.mainservice.model.exception.DobrostoreInternalException;

import java.sql.Timestamp;
import java.time.Instant;

@Slf4j
@RestControllerAdvice
public class InternalExceptionHandler {

    @ExceptionHandler({DobrostoreInternalException.class})
    public ErrorResponse handleInternalException(DobrostoreInternalException e) {
        log.error(e.getMessage());
        ExceptionType exceptionType = e.getClass()
                .getAnnotation(InternalException.class)
                .type();

        return ErrorResponse.builder()
                .code(exceptionType.getCode())
                .status(exceptionType.getHttpStatus())
                .message(e.getMessage())
                .timestamp(Timestamp.from(Instant.now()))
                .build();
    }
}
