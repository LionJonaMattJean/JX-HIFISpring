package com.jxhifi.jxhifispring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class JxHifiSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(JxHifiSpringApplication.class, args);
    }

}
