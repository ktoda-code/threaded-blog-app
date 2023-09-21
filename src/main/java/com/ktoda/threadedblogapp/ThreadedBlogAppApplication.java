package com.ktoda.threadedblogapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ThreadedBlogAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThreadedBlogAppApplication.class, args);
        System.out.println("""
                =================================
                =================================
                Threaded Blog Application started
                =================================
                =================================
                """);
    }

}
