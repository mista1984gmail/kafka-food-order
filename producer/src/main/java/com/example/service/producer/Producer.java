package com.example.service.producer;

import com.example.domain.FoodOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Producer {
    @Value("${topic.name}")
    private String orderTopic;

    @Value("${topic.name-scheduler}")
    private String orderTopicScheduler;

    private Faker faker = new Faker();

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public Producer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public String sendMessage(FoodOrder foodOrder) throws JsonProcessingException {
        String orderAsMessage = objectMapper.writeValueAsString(foodOrder);
        kafkaTemplate.send(orderTopic, orderAsMessage);

        log.info("food order produced {}", orderAsMessage);

        return "message sent";
    }

    @Scheduled(fixedRate = 1000)
    public void sendMessageOnScheduled() throws JsonProcessingException {

        FoodOrder foodOrder = new FoodOrder(faker.food().dish(), faker.number().randomDouble(0,1,5));
        String orderAsMessage = objectMapper.writeValueAsString(foodOrder);
        kafkaTemplate.send(orderTopicScheduler, orderAsMessage);

        log.info("food order produced {}", orderAsMessage);


    }

}
