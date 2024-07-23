package com.example.blogservice.service;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class BlogServiceUtils {
    public String getUUID() {
        return UUID.randomUUID().toString();
    }

    public Instant getCreatedDate() {
        return Instant.now();
    }

    public Instant getUpdatedDate() {
        return Instant.now();
    }
}
