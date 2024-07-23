package com.example.blogservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogResponse {

    private String id;
    private Integer userId;
    private String userName;
    private String email;
    private String title;
    private String content;
    private Instant createdAt;
    private Instant updateAt;
}
