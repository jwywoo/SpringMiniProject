package com.example.postcommnetspringsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PostCommnetSpringsecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostCommnetSpringsecurityApplication.class, args);
    }

}
