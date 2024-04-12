package com.metlife.filter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import reactor.core.publisher.Mono;

@Component
public class JwtAuthServerRestClient {

    @Autowired
    private RestTemplate restTemplate;

    public Mono<Boolean> validate(String token){
        ResponseEntity<Boolean> responseEntity = restTemplate.getForEntity(
                "http://localhost:8081/api/auth/validate/{token}",
                Boolean.class,
                token);
        return  Mono.just(responseEntity.getBody());
    }
}
