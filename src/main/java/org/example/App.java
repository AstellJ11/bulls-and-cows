package org.example;

import org.example.controllers.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class App implements CommandLineRunner {

    @Autowired
    Controller controllers;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    // Run the local console version of the application
    @Override
    public void run(String... args) throws Exception {
        controllers.runProgram();

        SpringApplication.run(App.class, args).close();  // Terminate the Spring Boot application cleanly
    }


}
