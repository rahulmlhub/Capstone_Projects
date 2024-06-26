package com.metlife;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableDiscoveryClient
//@CrossOrigin(origins = "http://localhost:4200")
public class HotelApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelApiGatewayApplication.class, args);
    }

}
