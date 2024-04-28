package com.kenm.spring.farmleaseservice.exception;

public class ResourceAlreadyExistsException extends RuntimeException {

    public ResourceAlreadyExistsException(String message) {
        super(message);
    }

    public ResourceAlreadyExistsException(String message, Throwable t) {
        super(message, t);
    }

    public ResourceAlreadyExistsException(Throwable t) {
        super(t);
    }

    public ResourceAlreadyExistsException(String message, Throwable t, boolean enableSuppression, boolean writableStackTrace) {
        super(message, t, enableSuppression, writableStackTrace);
    }
}

