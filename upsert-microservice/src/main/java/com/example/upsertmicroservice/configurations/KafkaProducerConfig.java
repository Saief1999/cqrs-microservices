package com.example.upsertmicroservice.configurations;

import com.example.upsertmicroservice.pojos.UpdateMessage;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value("${kafka.host}")
    private String kafkaHost;

    @Value("${kafka.topic}")
    private String projectorTopic;

    @Value("${kafka.partitions}")
    private int partitions ;

    @Value("${kafka.replicas}")
    private int replicas ;

    @Bean
    public ProducerFactory<String, UpdateMessage> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                kafkaHost);
        JsonSerializer<UpdateMessage> jsonSerializer = new JsonSerializer<>();
        Map<String, Object> idMappings = new HashMap<>();

        // we map UpdateMessage -> Our Class Instance
        idMappings.put(JsonSerializer.TYPE_MAPPINGS,
                "UpdateMessage:"+UpdateMessage.class.getName());

        jsonSerializer.configure(idMappings, false);
        return new DefaultKafkaProducerFactory<String, UpdateMessage>(configProps, new StringSerializer(), jsonSerializer);
    }

    @Bean NewTopic cqrsTopic() {
            return TopicBuilder.name(projectorTopic)
                    .partitions(partitions)
                    .replicas(replicas)
                    .build();
    }

    @Bean
    public KafkaTemplate<String, UpdateMessage> kafkaTemplate() {
        return new KafkaTemplate<String,UpdateMessage>(producerFactory());
    }
}
