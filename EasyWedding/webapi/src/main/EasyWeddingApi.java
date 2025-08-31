package com.easywedding.webapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.easywedding.infrastructure.jpa.repositories")
@EntityScan(basePackages = "com.easywedding.infrastructure.jpa.entities")
@ComponentScan(basePackages = {
        "com.easywedding.webapi",
        "com.easywedding.application",
        "com.easywedding.infrastructure"
})
public class EasyWeddingApi {
    public static void main(String[] args) {
        SpringApplication.run(EasyWeddingApi.class, args);
    }
}
