package com.example.studentapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentApi {

    public static void main(String[] args) {
        // Starts Spring and the embedded web server.
        SpringApplication.run(StudentApi.class, args);
    }

}
