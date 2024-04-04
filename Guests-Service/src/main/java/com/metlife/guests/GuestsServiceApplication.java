package com.metlife.guests;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GuestsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuestsServiceApplication.class, args);
    }

}
