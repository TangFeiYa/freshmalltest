package com.freshmall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AdminWebApp {
    public static void main(String[] args) {
        SpringApplication.run(AdminWebApp.class);
    }
}
