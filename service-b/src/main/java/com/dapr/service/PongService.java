package com.dapr.service;

import com.dapr.dto.Response;
import com.dapr.util.DbLoggerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Service for processing ping requests in Service B.
 */
@Service
public class PongService {
    private static final Logger logger = LoggerFactory.getLogger(PongService.class);

    private final DbLoggerUtil dbLoggerUtil;

    public PongService(DbLoggerUtil dbLoggerUtil) {
        this.dbLoggerUtil = dbLoggerUtil;
    }

    /**
     * Processes the incoming ping request.
     *
     * @param payload the incoming request payload
     * @return a Response object containing the result
     */
    public Response processPing(Map<String, Object> payload) {
        logger.info("Processing ping request with payload: {}", payload);

        String message = (String) payload.get("message");
        if (message == null || message.isEmpty()) {
            throw new IllegalArgumentException("Message field is missing or empty");
        }

        dbLoggerUtil.log("Service B received: " + message);
        logger.info("Ping request processed successfully");

        return new Response("pong");
    }
}
