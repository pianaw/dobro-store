package ru.kpfu.ds.mainservice.model.annotation;

import ru.kpfu.ds.mainservice.model.enums.ExceptionType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface InternalException {

    ExceptionType type() default ExceptionType.UNKNOWN;
}
