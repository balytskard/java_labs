package com.example.lab4_2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class Lab42Application {

    public static void main(String[] args) {
        SpringApplication.run(Lab42Application.class, args);
    }

}
