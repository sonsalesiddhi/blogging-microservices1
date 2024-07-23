package com.example.notificationservice;

import com.example.notificationservice.model.LikeRequest;
import com.example.notificationservice.model.LikeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Slf4j
public class NotificationServiceApplication {

    @Autowired
    private RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

    @KafkaListener(topics = "topic")
    public void handleLikeEvent(LikeEvent likeEvent){
        log.info("received msg"+likeEvent.getBlogId() +" "+ likeEvent.getUserId());
        String uri = "http://localhost:8404/api/like/";

        restTemplate.postForEntity(uri, new LikeRequest(likeEvent.getBlogId(), likeEvent.getUserId()), LikeResponse.class);
    }

}
