package com.chatgpt.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate template() {
    RestTemplate restTemplate = new RestTemplate();

    restTemplate.getInterceptors().add(((request, body, execution) -> {
        request.getHeaders().add("Authorization",
                "Bearer "+"key");
        return execution.execute(request,body);
    }));

        return new RestTemplate();
    }
}


