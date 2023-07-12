package com.app.shipment.ordermanagment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientBuild {

    @Bean
    public WebClient inventoryWebClient() {
        return WebClient.builder().baseUrl("http://localhost:8080").build();
    }

    @Bean
    public WebClient customerWebClient() {
        return WebClient.builder().baseUrl("http://localhost:8081").build();
    }

    @Bean
    public WebClient orderWebClient() {
        return WebClient.builder().baseUrl("http://localhost:8082").build();
    }
}
