package com.dapr.service;

import com.dapr.model.ResponseModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class RestServiceBClient {
    private static final Logger logger = LoggerFactory.getLogger(RestServiceBClient.class);

    private static final String DAPR_BASE_URL = "http://service-b-dapr:3501/v1.0/invoke/service-b/method/service-b/ping";

    private final ObjectMapper objectMapper = new ObjectMapper();
    /**
     * Invokes the ping method of Service B using REST.
     *
     * @return the response from Service B
     */
    public ResponseModel invokeServiceB() {
        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, String> payload = new HashMap<>();
            payload.put("message", "ping from Service A");

            HttpEntity<Map<String, String>> request = new HttpEntity<>(payload, headers);

            logger.info("Sending request to Service B via Dapr sidecar...");

            ResponseEntity<String> response = restTemplate.postForEntity(DAPR_BASE_URL, request, String.class);

            logger.info("Response from Service B: {}", response.getBody());
            return objectMapper.readValue(response.getBody(), ResponseModel.class);
        } catch (Exception e) {
            logger.error("Error invoking Service B via RestTemplate: {}", e.getMessage(), e);
            return new ResponseModel("Error: " + e.getMessage());
        }
    }
}
