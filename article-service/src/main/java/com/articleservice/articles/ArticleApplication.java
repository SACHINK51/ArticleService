package com.articleservice.articles; // Package declaration for organizing related classes

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class to start the Spring Boot application.
 * The `@SpringBootApplication` annotation enables auto-configuration, component scanning, and configuration support.
 */
@SpringBootApplication
public class ArticleApplication {

    /**
     * The main entry point of the application.
     * The `SpringApplication.run()` method initializes and starts the Spring Boot application.
     * @param args Command-line arguments passed when starting the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(ArticleApplication.class, args); // Starts the Spring Boot application
    }
}
