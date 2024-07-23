package com.example.commentservice.controller;

import com.example.commentservice.model.CommentRequest;
import com.example.commentservice.model.CommentResponse;
import com.example.commentservice.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public CommentResponse addComment(@RequestBody CommentRequest commentRequest){
        return commentService.addComment(commentRequest);
    }

    @GetMapping("{blogId}")
    public List<CommentResponse> getCommentByBlogId(@PathVariable String blogId ){
        return commentService.getCommentByBlogId(blogId);
    }

    @GetMapping("{userId}")
    public List<CommentResponse> getCommentByUserId(@PathVariable Integer userId ){
        return commentService.getCommentByUserId(userId);

    }

}
