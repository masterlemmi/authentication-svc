package com.lemzki.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@ComponentScan(basePackages = {"com.lemzki"})
@SpringBootApplication
@RestController
public class AuthenticationSvcApplication {

    @Autowired
    private Environment env;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {
        SpringApplication.run(AuthenticationSvcApplication.class, args);
    }

    @Bean
    ApplicationRunner checkRequirements(){
        return args -> {
            String secret = env.getProperty("LT_SECRET");
            if(secret == null || secret.contains(" ")){
                logger.error("SECRET KEY may be incorrectly configured, You will run into problems with authentication");
            } else {
                logger.info( "Secret key configured correctly");
            }
        };
    }

    @GetMapping("/test2")
    public String test2(){
        return "This is atuhenticated!";
    }

    @GetMapping("/test")
    public String test(){
        return "This is PERMIT ALL";
    }
}