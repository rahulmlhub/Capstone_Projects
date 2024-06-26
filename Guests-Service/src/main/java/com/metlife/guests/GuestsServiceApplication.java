package com.metlife.guests;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * @author Admin
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GuestsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuestsServiceApplication.class, args);
    }

}
