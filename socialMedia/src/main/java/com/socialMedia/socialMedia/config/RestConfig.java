package com.socialMedia.socialMedia.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Configuration
public class RestConfig {

    @Value("${external.api.tokenType}")
    private String tokenType;

    @Value("${external.api.accessToken}")
    private String accessToken;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        String authHeader = tokenType + " " + accessToken;

        ClientHttpRequestInterceptor authInterceptor = (request, body, execution) -> {
            request.getHeaders().add("Authorization", authHeader);
            return execution.execute(request, body);
        };
        restTemplate.setInterceptors(Collections.singletonList(authInterceptor));

        return restTemplate;
    }
}
