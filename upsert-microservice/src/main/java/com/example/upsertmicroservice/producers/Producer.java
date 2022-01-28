package com.example.upsertmicroservice.producers;

import com.example.upsertmicroservice.pojos.UpdateMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class Producer {

    @Value("${kafka.topic}")
    private String projectorTopic;

    private KafkaTemplate<String, UpdateMessage> kafkaTemplate;

    public Producer(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendUpdate(UpdateMessage updateMessage) {
        System.out.println(String.format("#### -> Producing message -> %s", updateMessage));
        this.kafkaTemplate.send(projectorTopic, updateMessage);
    }
}
