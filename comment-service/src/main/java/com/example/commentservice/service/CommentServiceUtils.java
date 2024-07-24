package com.example.commentservice.service;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class CommentServiceUtils {


    public Instant getCreatedDate() {
        return Instant.now();
    }

    public Instant getUpdatedDate() {
        return Instant.now();
    }
}
