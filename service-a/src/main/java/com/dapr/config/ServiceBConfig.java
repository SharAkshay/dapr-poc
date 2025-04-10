package com.dapr.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "serviceb")
public class ServiceBConfig {
    private String appId;
    private String method;

    public ServiceBConfig() {
        // Default constructor for Spring Boot binding
    }

    public ServiceBConfig(String appId, String method) {
        this.appId = appId;
        this.method = method;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
