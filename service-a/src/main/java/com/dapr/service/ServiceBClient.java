package com.dapr.service;

import com.dapr.config.ServiceBConfig;
import com.dapr.exception.ServiceInvocationException;
import com.dapr.model.PingPayload;
import com.dapr.model.ResponseModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dapr.client.DaprClient;
import io.dapr.client.domain.HttpExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ServiceBClient {
    private static final Logger logger = LoggerFactory.getLogger(ServiceBClient.class);

    private static final String INVOKING_SERVICE_LOG = "Invoking Service B with payload: {}";
    private static final String SERVICE_CONFIG_LOG = "ServiceB App ID: {}, Method: {}";
    private static final String ERROR_LOG = "Error invoking Service B: {}";

    private final DaprClient daprClient;
    private final ObjectMapper objectMapper;
    private final ServiceBConfig serviceBConfig;

    public ServiceBClient(DaprClient daprClient, ObjectMapper objectMapper, ServiceBConfig serviceBConfig) {
        this.daprClient = daprClient;
        this.objectMapper = objectMapper;
        this.serviceBConfig = serviceBConfig;

        validateConfig();
    }
    /**
     * Validates the ServiceBConfig properties.
     */
    private void validateConfig() {
        if (!StringUtils.hasText(serviceBConfig.getAppId())) {
            throw new IllegalArgumentException("ServiceB App ID must not be null or empty");
        }
        if (!StringUtils.hasText(serviceBConfig.getMethod())) {
            throw new IllegalArgumentException("ServiceB Method must not be null or empty");
        }
    }
    /**
     * Invokes the ping method of Service B.
     *
     * @return the response from Service B
     */
    public ResponseModel invokeServiceB() {
        try {
            PingPayload payload = new PingPayload("ping from Service A");
            String payloadJson = objectMapper.writeValueAsString(payload);
            logger.info(INVOKING_SERVICE_LOG, payloadJson);
            logger.info(SERVICE_CONFIG_LOG, serviceBConfig.getAppId(), serviceBConfig.getMethod());
            byte[] responseBody = daprClient.invokeMethod(
                    serviceBConfig.getAppId(),
                    serviceBConfig.getMethod(),
                    payloadJson,
                    HttpExtension.POST,
                    null,
                    byte[].class).block();
            if (responseBody == null) {
                logger.warn("Received null response from Service B");
                return new ResponseModel("Error: Null response from Service B");
            }
            return objectMapper.readValue(responseBody, ResponseModel.class);
        } catch (Exception e) {
            logger.error(ERROR_LOG, e.getMessage(), e);
            throw new ServiceInvocationException("Failed to invoke serviceB", e);
        }
    }
}
