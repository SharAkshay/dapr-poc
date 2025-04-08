package com.dapr.exception;

public class ServiceBFallbackException extends RuntimeException {
    public ServiceBFallbackException(String message) {
        super(message);
    }
}
