package com.likangmin;

import com.likangmin.filter.AccessPasswordFilter;
import com.likangmin.filter.AccessTokenFilter;
import com.likangmin.filter.AccessUserNameFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * @author likangmin
 * @date 2019/5/13-17:12
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class AppGateWay {
    // @EnableZuulProxy 开启网关代理

    public static void main(String[] args) {
        SpringApplication.run(AppGateWay.class, args);
    }

    @Bean
    public AccessUserNameFilter accessUserNameFilter() {
        return new AccessUserNameFilter();
    }
    @Bean
    public AccessPasswordFilter accessPasswordFilter(){
        return new AccessPasswordFilter();
    }
    @Bean
    public AccessTokenFilter accessTokenFilter(){
        return new AccessTokenFilter();
    }
//    // zuul配置能够使用config实现实时更新
////    @RefreshScope
////    @ConfigurationProperties("zuul")
////    public ZuulProperties zuulProperties() {
////        return new ZuulProperties();
////    }

}
