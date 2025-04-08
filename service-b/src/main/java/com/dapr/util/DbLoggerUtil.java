package com.dapr.util;

import com.dapr.entity.RequestLogEntity;
import com.dapr.exception.DatabaseLoggingException;
import com.dapr.repository.RequestLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DbLoggerUtil {

    private final RequestLogRepository requestLogRepository;

    private static final Logger logger = LoggerFactory.getLogger(DbLoggerUtil.class);

    public DbLoggerUtil(RequestLogRepository requestLogRepository) {
        this.requestLogRepository = requestLogRepository;
    }

    public void log(String message) {
        try {
            // Create an entity object and save it using the repository
            RequestLogEntity logEntry = new RequestLogEntity("Service B", message);
            requestLogRepository.save(logEntry);
        } catch (Exception e) {
            logger.error("Failed to log message to the database: {}", message, e);
            throw new DatabaseLoggingException("Error logging to the database", e);
        }
    }
}
