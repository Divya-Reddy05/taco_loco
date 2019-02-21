package com.taco.loco.application;

import com.taco.loco.controller.interceptor.EnforceHttpsInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@SpringBootApplication(scanBasePackages = "com.taco.loco")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    @Primary
    public EnforceHttpsInterceptor enforceHttpsInterceptor() {
        return new EnforceHttpsInterceptor();
    }
}
