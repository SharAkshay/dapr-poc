package com.dapr.controller;

import com.dapr.model.ResponseModel;
import com.dapr.service.ServiceBClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service-a")
public class PingController {

    private static final Logger logger = LoggerFactory.getLogger(PingController.class);

    private final ServiceBClient serviceBClient;

    @Autowired
    public PingController(ServiceBClient serviceBClient) {
        this.serviceBClient = serviceBClient;
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
            ResponseModel response = serviceBClient.invokeServiceB();

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
