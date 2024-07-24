package com.example.commentservice.service;

import com.example.commentservice.entity.Comment;
import com.example.commentservice.exception.BlogNotFoundException;
import com.example.commentservice.exception.UserNotFoundException;
import com.example.commentservice.external.BlogService;
import com.example.commentservice.external.UserService;
import com.example.commentservice.external.model.BlogResponse;
import com.example.commentservice.external.model.UserResponse;
import com.example.commentservice.model.CommentRequest;
import com.example.commentservice.model.CommentResponse;
import com.example.commentservice.controller.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private CommentServiceUtils commentServiceUtils;

    public CommentResponse addComment(CommentRequest commentRequest) {
        Instant createdDate = commentServiceUtils.getCreatedDate();


        UserResponse userResponse = userService.getUserById(commentRequest.getUserId());
        if(Objects.isNull(userResponse)){
            throw new UserNotFoundException("user with"+commentRequest.getUserId()+"not found");
        }

        BlogResponse blogResponse = blogService.getBlogById(commentRequest.getBlogId());
        if(Objects.isNull(blogResponse)){
            throw new BlogNotFoundException("Blog with"+commentRequest.getBlogId()+"not found");
        }

        Comment comment = Comment.builder()
                .blogId(commentRequest.getBlogId())
                .userId(commentRequest.getUserId())
                .content(commentRequest.getContent())
                .createdAt(createdDate)
                .build();

        Comment savedComment = commentRepository.save(comment);
        CommentResponse commentResponse = CommentResponse.builder()
                .id(savedComment.getId())
                .blogId(savedComment.getBlogId())
                .blogTitle(blogResponse.getTitle())
                .blogContent(blogResponse.getContent())
                .userId(savedComment.getUserId())
                .userName(userResponse.getUserName())
                .userEmail(userResponse.getEmail())
                .content(savedComment.getContent())
                .createdAt(savedComment.getCreatedAt())
                .build();

        return commentResponse;
    }

    public List<CommentResponse> getCommentByBlogId(String blogId) {

        List<Comment> comments = commentRepository.findAllByBlogId(blogId);
        List<CommentResponse> commentResponseList = new ArrayList<>();

        BlogResponse blogResponse =blogService.getBlogById(blogId);


        for(Comment comment:comments){
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
            commentResponseList.add(commentResponse);
        }
        return commentResponseList;
    }

    public List<CommentResponse> getCommentByUserId(Integer userId) {
        List<Comment> comments = commentRepository.findAllByUserId(userId);
        /*
        * id blogid userid content createdat
        * 1    1      1      xyz    xyz
        * 2    1      1      ghj    gjhg
        * 3    2      1      xyz   hjk
        * 4    2      2      xyz    xyz
        *
        * */
        List<CommentResponse> commentResponseList = new ArrayList<>();

        for(Comment comment:comments){
            BlogResponse blogResponse = blogService.getBlogById(comment.getBlogId());
            UserResponse userResponse = userService.getUserById(comment.getUserId());
            CommentResponse commentResponse = CommentResponse.builder()
                    .id(comment.getId())
                    .blogId(comment.getBlogId())
                    .blogTitle(blogResponse.getTitle())
                    .blogContent(blogResponse.getContent())
                    .userId(comment.getUserId())
                    .userName(userResponse.getUserName())
                    .userEmail(userResponse.getEmail())
                    .createdAt(comment.getCreatedAt())
                    .content(comment.getContent())
                    .build();
            commentResponseList.add(commentResponse);
        }
        return commentResponseList;

    }
}
