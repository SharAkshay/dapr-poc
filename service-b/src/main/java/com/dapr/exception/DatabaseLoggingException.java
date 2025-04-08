package com.dapr.exception;

public class DatabaseLoggingException extends RuntimeException {

    public DatabaseLoggingException(String message) {
        super(message);
    }

    public DatabaseLoggingException(String message, Throwable cause) {
        super(message, cause);
    }
}
