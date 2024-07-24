package com.example.commentservice.service;

import com.example.commentservice.controller.repository.CommentRepository;
import com.example.commentservice.entity.Comment;
import com.example.commentservice.external.BlogService;
import com.example.commentservice.external.UserService;
import com.example.commentservice.external.model.BlogResponse;
import com.example.commentservice.external.model.UserResponse;
import com.example.commentservice.model.CommentRequest;
import com.example.commentservice.model.CommentResponse;
import com.netflix.discovery.converters.Auto;
import org.hibernate.service.spi.InjectService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {
    @InjectMocks
    private CommentService commentService;

    @Mock
    private UserService userService;

    @Mock
    private BlogService blogService;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private CommentServiceUtils commentServiceUtils;

    @Test
    void addComment() {

        Instant time= Instant.now();
        Mockito.when(commentServiceUtils.getCreatedDate()).thenReturn(time);

        UserResponse userResponse = UserResponse.builder()
                .id(1)
                .userName("sid")
                .email("sid@gmail.com")
                .password("12345")
                .build();


        BlogResponse blogResponse = BlogResponse.builder()

                .content("nice blog")
                .title("blog1")
                .build();

        Comment comment = Comment.builder()
                .blogId("blog123")
                .userId(1)
                .content("comment content")
                .createdAt(time)
                .build();

        CommentRequest commentRequest = CommentRequest.builder()
                .blogId("blog123")
                .content("comment content")
                .userId(1)
                .build();

        Mockito.when(userService.getUserById(commentRequest.getUserId())).thenReturn(userResponse);
        Mockito.when(blogService.getBlogById(commentRequest.getBlogId())).thenReturn(blogResponse);

        Mockito.when(commentRepository.save(comment)).thenReturn(comment);
        CommentResponse expectedCommentResponse = CommentResponse.builder()
                .blogId("blog123")
                .blogTitle(blogResponse.getTitle())
                .blogContent(blogResponse.getContent())
                .userId(1)
                .userName(userResponse.getUserName())
                .userEmail(userResponse.getEmail())
                .content("comment content")
                .createdAt(time)
                .build();

        CommentResponse actualCommentResponse = commentService.addComment(commentRequest);

        Assertions.assertEquals(expectedCommentResponse,actualCommentResponse);
    }

    @Test
    void getCommentByBlogId() {
        List<Comment> comments = new ArrayList<>();
        List<CommentResponse> expectedCommentResponseList = new ArrayList<>();

        Instant time= Instant.now();
        Mockito.when(commentServiceUtils.getCreatedDate()).thenReturn(time);

        Comment comment = Comment.builder()
                .blogId("blog123")
                .userId(1)
                .content("comment content")
                .createdAt(commentServiceUtils.getCreatedDate())
                .build();

        comments.add(comment);

        BlogResponse blogResponse = BlogResponse.builder()
                .id("blog123")
                .userName("siddhi")
                .email("Sid@gmail.com")
                .content(comment.getContent())
                .content("nice blog")
                .title("blog1")
                .userId(1)
                .createdAt(commentServiceUtils.getCreatedDate())
                .build();

        Mockito.when(commentRepository.findAllByBlogId("blog123")).thenReturn(comments);
        Mockito.when(blogService.getBlogById("blog123")).thenReturn(blogResponse);

        CommentResponse commentResponse = CommentResponse.builder()
                .id(comment.getId())
                .blogId(comment.getBlogId())
                .blogTitle(blogResponse.getTitle())
                .blogContent(blogResponse.getContent())
                .userId(comment.getUserId())
                .userName(blogResponse.getUserName())
                .userEmail(blogResponse.getEmail())
                .createdAt(comment.getCreatedAt())
                .content(comment.getContent())
                .build();

        expectedCommentResponseList.add(commentResponse);

       List<CommentResponse> actualCommentResponse = commentService.getCommentByBlogId("blog123");

       Assertions.assertEquals(expectedCommentResponseList,actualCommentResponse);

    }
}