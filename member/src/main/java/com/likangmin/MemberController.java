package com.likangmin;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author likangmin
 * @date 2019/5/15-14:50
 */
@RestController
@EnableEurekaClient
@SpringBootApplication
public class MemberController {

    @Value("${server.port}")
    private String serverPort;


    public static void main(String[] args) {
        SpringApplication.run(MemberController.class, args);
    }

   @RequestMapping("/")
    public String index(HttpServletRequest req) {
        System.out.println("我是一个服务，需要对应的权限才可以查看.....");
        return "我是一个服务" + serverPort;
    }



}
