package com.example.blogservice.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document

public class Blog {
    private String id;
    private Integer userId;
    private String title;
    private String content;
    private Instant createdAt;
    private Instant updateAt;
}
