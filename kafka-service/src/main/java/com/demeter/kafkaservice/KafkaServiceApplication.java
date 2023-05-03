package com.demeter.kafkaservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@Slf4j
public class KafkaServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(KafkaServiceApplication.class, args);
    }
    @KafkaListener(topics = "dbEventTopic")
    public void handleNotification(RecipeAddedEvent recipeAddedEvent) {
        // send out an email notification
        log.info("Received Notification for Recipe - {}", recipeAddedEvent.getRecipeNumber());
    }
}
