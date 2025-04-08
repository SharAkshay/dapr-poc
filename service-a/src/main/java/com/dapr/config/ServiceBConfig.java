package com.dapr.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "serviceb")
public class ServiceBConfig {
    private final String appId;
    private final String method;

    public ServiceBConfig(String appId, String method) {
        this.appId = appId;
        this.method = method;
    }

    public String getAppId() {
        return appId;
    }

    public String getMethod() {
        return method;
    }
}
