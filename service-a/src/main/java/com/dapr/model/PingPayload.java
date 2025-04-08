package com.dapr.model;

public class PingPayload {
    private String message;

    public PingPayload(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
