package com.example.blogservice.controller;

import com.example.blogservice.externel.model.LikeRequest;
import com.example.blogservice.externel.model.UserResponse;
import com.example.blogservice.model.BlogRequest;
import com.example.blogservice.model.BlogResponse;
import com.example.blogservice.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blog/")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @PostMapping
    public BlogResponse createBlog(@RequestBody BlogRequest blogRequest){
        return blogService.createBlog(blogRequest);
    }

    @GetMapping("{id}")
    public BlogResponse getBlogById(@PathVariable String id){
        return blogService.getBlogById(id);
    }

    @GetMapping("{userId}")
    public List<BlogResponse> getBlogByUserId(@PathVariable Integer userId){
        return blogService.getBlogByUserId(userId);
    }

    @GetMapping("blogs")
    public List<BlogResponse> getAllBlogs(){
        return blogService.getAllBlogs();
    }

    @PostMapping("likes")
    public String likeBlog(@RequestBody LikeRequest likeRequest){
        return blogService.likeBlog(likeRequest);
    }


}
