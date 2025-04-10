package com.dapr.model;

public class ResponseModel {
    private String message;
    public ResponseModel() {
    }
    public ResponseModel(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResponseModel{" +
                "message='" + message + '\'' +
                '}';
    }
}
