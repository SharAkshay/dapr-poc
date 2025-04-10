package com.dapr.util;

import com.dapr.entity.RequestLogEntity;
import com.dapr.exception.DatabaseLoggingException;
import com.dapr.repository.RequestLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Utility class for logging messages to the database.
 */
@Component
public class DbLoggerUtil {
    private static final Logger logger = LoggerFactory.getLogger(DbLoggerUtil.class);

    private final RequestLogRepository requestLogRepository;

    public DbLoggerUtil(RequestLogRepository requestLogRepository) {
        this.requestLogRepository = requestLogRepository;
    }

    /**
     * Logs a message to the database.
     *
     * @param message the message to log
     * @throws DatabaseLoggingException if an error occurs while logging to the database
     */
    public void log(String message) {
        if (!StringUtils.hasText(message)) {
            logger.warn("Invalid log message: Message is null or empty");
            throw new IllegalArgumentException("Log message must not be null or empty");
        }
        try {
            logger.info("Logging message to the database: {}", message);
            RequestLogEntity logEntry = new RequestLogEntity("Service B", message);
            requestLogRepository.save(logEntry);
            logger.info("Message logged successfully to the database");
        } catch (Exception e) {
            logger.error("Failed to log message to the database: {}", message, e);
            throw new DatabaseLoggingException("Error logging to the database", e);
        }
    }
}
