package com.example.likeservice.controller;

import com.example.likeservice.model.LikeRequest;
import com.example.likeservice.model.LikeResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LikeControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
     private TestRestTemplate testRestTemplate;

    @Test
    void getLikeCount() {

        Integer likeCount = testRestTemplate.getForEntity("/api/like/{blogId}",Integer.class,"blog123").getBody();

        Assertions.assertEquals(1,likeCount);
    }

    @Test
    void likeBlog() {

        LikeRequest likeRequest = LikeRequest.builder()
                .blogId("blog123")
                .userId(1)
                .build();

        LikeResponse actualLikeResponse =testRestTemplate.postForEntity("/api/like/",likeRequest, LikeResponse.class ).getBody();

        LikeResponse expectedlikeResponse = LikeResponse.builder()
                .userId(1)
                .blogId("blog123")
                .build();

        Assertions.assertEquals(expectedlikeResponse.getBlogId(),actualLikeResponse.getBlogId());
    }
}