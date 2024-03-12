package com.ecommerce.serverconfiguration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServerConfigurationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerConfigurationApplication.class, args);
    }

}
