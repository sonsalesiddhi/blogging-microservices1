package com.example.likeservice.controller;

import com.example.likeservice.model.LikeRequest;
import com.example.likeservice.model.LikeResponse;
import com.example.likeservice.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/like/")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @PostMapping
    public LikeResponse likeBlog(@RequestBody LikeRequest likeRequest){
        return likeService.likeBlog(likeRequest);
    }

    /*
    * blogid userid
    * 1       1
    * 2       1
    * 1       2
    * */

    @GetMapping("{blogId}")
    public Integer getLikeCount(@PathVariable String blogId){
        return likeService.getLikeCount(blogId);
    }

}
