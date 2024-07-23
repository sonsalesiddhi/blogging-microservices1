package com.example.commentservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentResponse {
private Integer id;
private String blogId;
private String blogTitle;
private String blogContent;
private Integer userId;
private String userName;
private String userEmail;
private String content;
private Instant createdAt;

}
