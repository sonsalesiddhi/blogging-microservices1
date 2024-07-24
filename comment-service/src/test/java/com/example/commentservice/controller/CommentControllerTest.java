package com.example.commentservice.controller;

import com.example.commentservice.model.CommentRequest;
import com.example.commentservice.model.CommentResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CommentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void addComment() {

        CommentRequest commentRequest = CommentRequest.builder()
                .blogId("blog123")
                .content("comment content")
                .userId(1)
                .build();

        CommentResponse actualCommentResponse = testRestTemplate.postForEntity("/api/comment",commentRequest, CommentResponse.class).getBody();

        CommentResponse expectedCommentResponse = CommentResponse.builder()
                
                .build();
    }
}