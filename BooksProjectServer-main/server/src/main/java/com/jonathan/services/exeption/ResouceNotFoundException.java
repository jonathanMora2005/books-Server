package com.jonathan.services.exeption;

public class ResouceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ResouceNotFoundException() {
    }

    public ResouceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResouceNotFoundException(String message) {
        super(message);
    }

    public ResouceNotFoundException(Throwable cause) {
        super(cause);
    }

    public ResouceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
