package com.metlife.facilities;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FacilitiesServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FacilitiesServiceApplication.class, args);
    }

}
