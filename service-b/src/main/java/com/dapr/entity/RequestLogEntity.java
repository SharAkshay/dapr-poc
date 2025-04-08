package com.dapr.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "request_details")
public class RequestLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatically increment ID
    private Long id;

    @Column(name = "service", nullable = false)
    private String service;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "timestamp", updatable = false, insertable = false)
    private LocalDateTime timestamp;

    public RequestLogEntity() {
        // Default constructor for JPA
    }

    public RequestLogEntity(String service, String message) {
        this.service = service;
        this.message = message;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
