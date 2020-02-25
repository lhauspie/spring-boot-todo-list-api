package com.example.todo.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        Optional.empty()
            .map(o -> o)
            .orElseThrow(() -> new RuntimeException("toto"));
        SpringApplication.run(DemoApplication.class, args);
    }

}
