package com.likangmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author likangmin
 * @date 2019/5/13-17:07
 */
@SpringBootApplication
@EnableEurekaServer
public class AppEurekaServer {
    public static void main(String[] args) {
        SpringApplication.run(AppEurekaServer.class, args);
    }
}
