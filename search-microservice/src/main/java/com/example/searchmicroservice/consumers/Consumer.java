package com.example.searchmicroservice.consumers;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Consumer
{
    @KafkaListener(topics = "${kafka.topic}", groupId = "${kafka.group-id}")
    public void consume(String message) throws IOException {
        System.out.println(String.format("#### -> Consumed message -> %s", message));
    }
}
