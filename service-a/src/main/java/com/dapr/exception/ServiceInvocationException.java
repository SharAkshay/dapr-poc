package com.dapr.exception;

public class ServiceInvocationException extends RuntimeException {
    public ServiceInvocationException(String message, Throwable cause) {
        super(message, cause);
    }
}
