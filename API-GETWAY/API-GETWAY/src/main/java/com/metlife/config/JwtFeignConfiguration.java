package com.metlife.config;

import feign.Client;
import feign.Request;
import feign.Request.Options;
import feign.Response;

import java.io.IOException;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableFeignClients
public class JwtFeignConfiguration {

    @Bean
    public Client feignClient() {
        return new WebClientFeignClient();
    }
}

class WebClientFeignClient implements Client {
    private final WebClient webClient;

    WebClientFeignClient() {
        this.webClient = WebClient.create();
    }

    @Override
    public Response execute(Request request, Request.Options options) throws IOException {
        return null; // Not used, WebClient will handle requests
    }

}