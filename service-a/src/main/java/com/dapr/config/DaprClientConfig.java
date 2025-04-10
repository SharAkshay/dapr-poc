package com.dapr.config;

import io.dapr.client.DaprClient;
import io.dapr.client.DaprClientBuilder;
import io.dapr.client.resiliency.ResiliencyOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * Configuration class for creating a DaprClient bean.
 */
@Configuration
public class DaprClientConfig {

    /**
     * Creates and configures a DaprClient bean.
     *
     * @return a singleton instance of DaprClient
     */
    @Bean
    public DaprClient daprClient() {
        ResiliencyOptions resiliencyOptions = new ResiliencyOptions()
                .setMaxRetries(3)
                .setTimeout(Duration.ofSeconds(5));

        return new DaprClientBuilder()
                .withResiliencyOptions(resiliencyOptions)
                .build();
    }
}
