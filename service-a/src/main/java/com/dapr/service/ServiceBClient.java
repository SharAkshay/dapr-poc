package com.dapr.service;

import com.dapr.config.ServiceBConfig;
import com.dapr.exception.ServiceInvocationException;
import com.dapr.model.PingPayload;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dapr.client.DaprClient;
import io.dapr.client.domain.HttpExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ServiceBClient {
    private static final Logger logger = LoggerFactory.getLogger(ServiceBClient.class);

    private final DaprClient daprClient;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ServiceBConfig serviceBConfig;

    public ServiceBClient(DaprClient daprClient, ServiceBConfig serviceBConfig) {
        this.daprClient = daprClient;
        this.serviceBConfig = serviceBConfig;
    }

    /**
     * Invokes the ping method of Service B.
     *
     * @return the response from Service B
     */
    public String invokeServiceB() {
        try {
            PingPayload payload = new PingPayload("ping from Service A");
            String payloadJson = objectMapper.writeValueAsString(payload);
            logger.info("Invoking serviceB with payload: {}", payloadJson);
            logger.info("ServiceB App ID: {}", serviceBConfig.getAppId());
            logger.info("ServiceB Method: {}", serviceBConfig.getMethod());
            return daprClient.invokeMethod(serviceBConfig.getAppId(), serviceBConfig.getMethod(), payloadJson, HttpExtension.POST, String.class).block();
        } catch (Exception e) {
            logger.error("Error invoking serviceB: {}", e.getMessage(), e);
            throw new ServiceInvocationException("Failed to invoke serviceB", e);
        }
    }
}
