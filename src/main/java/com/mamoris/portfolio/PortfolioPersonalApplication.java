package com.mamoris.portfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class PortfolioPersonalApplication {

    public static void main(String[] args) {
        SpringApplication.run(PortfolioPersonalApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**").allowedOrigins("https://portfolio-fm.web.app/");
                registry.addMapping("/api/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH");
                /*registry.addMapping("/api/auth/**").allowedOrigins("*").
                        allowedMethods("GET", "POST");
                registry.addMapping("/api/edutacion/**").allowedOrigins("*").;*/

            }
        };
    }
}
