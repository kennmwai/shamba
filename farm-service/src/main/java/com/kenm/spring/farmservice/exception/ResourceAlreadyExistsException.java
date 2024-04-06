package com.kenm.spring.farmservice.exception;

public class ResourceAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

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
