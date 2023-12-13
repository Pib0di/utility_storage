package com.thewhite.utilitystorage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.thewhite.utilitystorage.util", "com.thewhite.utilitystorage"} )
public class Main {
    public static void main(String [] args) {
        SpringApplication.run(Main.class, args);
    }
}



