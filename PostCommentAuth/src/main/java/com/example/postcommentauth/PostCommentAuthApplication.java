package com.example.postcommentauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PostCommentAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostCommentAuthApplication.class, args);
    }

}
