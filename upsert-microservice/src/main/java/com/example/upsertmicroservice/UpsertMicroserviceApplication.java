package com.example.upsertmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableMongoRepositories
public class UpsertMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UpsertMicroserviceApplication.class, args);
    }

}
