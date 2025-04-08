package com.dapr.controller;

import com.dapr.exception.ServiceBFallbackException;
import com.dapr.service.ServiceBClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service-a")
public class PingController {

    private final ServiceBClient serviceBClient;

    @Autowired
    public PingController(ServiceBClient serviceBClient) {
        this.serviceBClient = serviceBClient;
    }

    @GetMapping("/ping")
    @CircuitBreaker(name="serviceB", fallbackMethod = "fallbackServiceB")
    public String pingServiceB() {
        return serviceBClient.invokeServiceB();
    }

    public String fallbackServiceB(Exception exception) {
        throw new ServiceBFallbackException("Service B is currently unavailable. Fallback triggered.");
    }
}
