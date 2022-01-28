package com.example.searchmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableElasticsearchRepositories
@EnableDiscoveryClient
public class SearchMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchMicroserviceApplication.class, args);
    }

}
