package com.dapr.controller;

import com.dapr.dto.Response;
import com.dapr.service.PongService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service-b")
public class PongController {

    private final PongService pongService;

    public PongController(PongService pongService) {
        this.pongService = pongService;
    }

    @PostMapping("/ping")
    public Response ping(@RequestBody String payload) {
        return pongService.processPing(payload);
    }
}
