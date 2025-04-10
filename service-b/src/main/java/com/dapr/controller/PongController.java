package com.dapr.controller;

import com.dapr.dto.Response;
import com.dapr.service.PongService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling requests to Service B.
 */
@RestController
@RequestMapping("/service-b")
public class PongController {
    private static final Logger logger = LoggerFactory.getLogger(PongController.class);

    private final PongService pongService;

    public PongController(PongService pongService) {
        this.pongService = pongService;
    }

    /**
     * Endpoint to process ping requests.
     *
     * @param payload the incoming request payload
     * @return ResponseEntity containing the response
     */
    @PostMapping("/ping")
    public ResponseEntity<Response> ping(@RequestBody String payload) {
        try {
            logger.info("Received ping request with payload: {}", payload);

            Response response = pongService.processPing(payload);
            logger.info("Processed ping request successfully: {}", response);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error processing ping request: {}", e.getMessage(), e);
            return ResponseEntity.status(500).body(new Response("Error: " + e.getMessage()));
        }
    }
}
