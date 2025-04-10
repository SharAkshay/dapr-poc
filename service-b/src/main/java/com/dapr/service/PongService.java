package com.dapr.service;

import com.dapr.dto.Response;
import com.dapr.util.DbLoggerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    public Response processPing(String payload) {
        logger.info("Processing ping request with payload: {}", payload);

        if (!StringUtils.hasText(payload)) {
            logger.warn("Invalid payload: Payload is null or empty");
            return new Response("Error: Invalid payload");
        }

        dbLoggerUtil.log("Service B received: " + payload);
        logger.info("Ping request processed successfully");

        return new Response("pong");
    }
}
