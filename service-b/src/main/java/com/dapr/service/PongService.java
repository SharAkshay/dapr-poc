package com.dapr.service;

import com.dapr.dto.Response;
import com.dapr.util.DbLoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PongService {
    private final DbLoggerUtil dbLoggerUtil;

    @Autowired
    public PongService(DbLoggerUtil dbLoggerUtil) {
        this.dbLoggerUtil = dbLoggerUtil;
    }

    public Response processPing(String payload) {
        dbLoggerUtil.log("Service B received: " + payload);
        return new Response("pong");
    }
}
