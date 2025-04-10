package com.dapr.config;

import io.dapr.client.DaprClient;
import io.dapr.client.DaprClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
        return new DaprClientBuilder().build();
    }
}
