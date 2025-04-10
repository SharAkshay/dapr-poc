package com.dapr.controller;

import com.dapr.model.ResponseModel;
import com.dapr.service.RestServiceBClient;
import com.dapr.service.ServiceBClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service-a")
public class PingController {

    private static final Logger logger = LoggerFactory.getLogger(PingController.class);

    private final ServiceBClient serviceBClient;
    private final RestServiceBClient restServiceBClient;

    @Value("${dapr.client.enabled}")
    private boolean daprClientEnabled;

    @Autowired
    public PingController(ServiceBClient serviceBClient, RestServiceBClient restServiceBClient) {
        this.serviceBClient = serviceBClient;
        this.restServiceBClient = restServiceBClient;
    }

    /**
     * Endpoint to ping Service B.
     *
     * @return ResponseEntity containing the response from Service B
     */
    @GetMapping("/ping")
    public ResponseEntity<ResponseModel> pingServiceB() {
        try {
            logger.info("Pinging Service B...");
            ResponseModel response;
            if (daprClientEnabled) {
                logger.info("Using ServiceBClient to invoke Service B.");
                response = serviceBClient.invokeServiceB();
            } else {
                logger.info("Using RestServiceBClient to invoke Service B.");
                response = restServiceBClient.invokeServiceB();
            }
            if (response == null) {
                logger.warn("Received null response from Service B");
                return ResponseEntity.status(500).body(new ResponseModel("Error: Null response from Service B"));
            }

            logger.info("Received response from Service B: {}", response);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error while pinging Service B", e);
            return ResponseEntity.status(500).body(new ResponseModel("Error: " + e.getMessage()));
        }
    }
}
